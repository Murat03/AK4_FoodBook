package com.muratipek.ak4_foodbook.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratipek.ak4_foodbook.model.Food

class FoodDetailViewModel: ViewModel() {
    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(){
        val banana = Food("Banana", "100", "50", "20", "10", "www.test.com")
        foodLiveData.value = banana
    }
}