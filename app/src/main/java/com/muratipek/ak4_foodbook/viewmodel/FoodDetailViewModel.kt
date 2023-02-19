package com.muratipek.ak4_foodbook.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.muratipek.ak4_foodbook.model.Food
import com.muratipek.ak4_foodbook.service.FoodDatabase
import kotlinx.coroutines.launch

class FoodDetailViewModel(application: Application): BaseViewModel(application) {
    val foodLiveData = MutableLiveData<Food>()

    fun getRoomData(uuid: Int){
        launch {
            val dao = FoodDatabase(getApplication()).foodDao()
            val food = dao.getFood(uuid)

            foodLiveData.value = food
        }
    }
}