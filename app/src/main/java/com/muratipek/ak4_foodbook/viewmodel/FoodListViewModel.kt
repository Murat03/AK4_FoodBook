package com.muratipek.ak4_foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratipek.ak4_foodbook.model.Food
import com.muratipek.ak4_foodbook.service.FoodAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class FoodListViewModel: ViewModel() {
    val foods = MutableLiveData<List<Food>>()
    val foodErrorMessage = MutableLiveData<Boolean>()
    val foodLoading = MutableLiveData<Boolean>()

    private val foodApiService = FoodAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        getDataFromInternet()

    }
    private fun getDataFromInternet(){
        foodLoading.value = true

        //IO, Default, UI

        disposable.add(foodApiService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<Food>>(){
                override fun onSuccess(t: List<Food>) {
                    foods.value = t
                    foodErrorMessage.value = false
                    foodLoading.value = false
                }

                override fun onError(e: Throwable) {
                    foodErrorMessage.value = true
                    foodLoading.value = false
                    e.printStackTrace()
                }

            })
        )
    }
}