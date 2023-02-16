package com.muratipek.ak4_foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratipek.ak4_foodbook.model.Food
import com.muratipek.ak4_foodbook.service.FoodAPIService
import io.reactivex.disposables.CompositeDisposable

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

    }
}