package com.muratipek.ak4_foodbook.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratipek.ak4_foodbook.model.Food
import com.muratipek.ak4_foodbook.service.FoodAPIService
import com.muratipek.ak4_foodbook.service.FoodDatabase
import com.muratipek.ak4_foodbook.util.SpecialSharedPreferences
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class FoodListViewModel(application: Application): BaseViewModel(application) {
    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()
    private var updateTime = 10 * 60 * 1000 * 1000 * 1000L

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()
    private val specialSharedPreferences = SpecialSharedPreferences(getApplication())

    fun refreshData(){
        val savedTime = specialSharedPreferences.getTime()
        if(savedTime != null && savedTime != 0L && System.nanoTime() - savedTime < updateTime){
            //Sqlite
            getDataFromSqlite()
        }else{
            //Internet
            getDataFromInternet()
        }
    }
    fun refreshFromInternet(){
        getDataFromInternet()
    }
    private fun getDataFromSqlite(){
        foodLoading.value = true
        launch {
            val foodList = FoodDatabase(getApplication()).foodDao().getAllFood()
            showFoods(foodList)
            Toast.makeText(getApplication(), "data From Room", Toast.LENGTH_LONG).show()
        }
    }
    private fun getDataFromInternet(){
        foodLoading.value = true

        //IO, Default, UI

        disposable.add(foodApiService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                override fun onSuccess(t: List<Food>) {
                    saveSqlite(t)
                    Toast.makeText(getApplication(), "data From Internet", Toast.LENGTH_LONG).show()
                }

                override fun onError(e: Throwable) {
                    foodErrorMessage.value = true
                    foodLoading.value = false
                    e.printStackTrace()
                }
            })
        )
    }
    private fun showFoods(foodsList: List<Food>){
        foods.value = foodsList
        foodErrorMessage.value = false
        foodLoading.value = false
    }
    private fun saveSqlite(foodList: List<Food>){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            dao.deleteAllFood()
            val uuidList = dao.insertAll(*foodList.toTypedArray())
            var i = 0
            while (i < foodList.size){
                foodList[i].uuid = uuidList[i].toInt()
                i = i + 1
            }
            showFoods(foodList)
        }
        specialSharedPreferences.saveTime(System.nanoTime())
    }
}