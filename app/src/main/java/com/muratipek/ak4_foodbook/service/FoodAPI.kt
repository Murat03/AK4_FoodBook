package com.muratipek.ak4_foodbook.service

import com.muratipek.ak4_foodbook.model.Food
import io.reactivex.Single
import retrofit2.http.GET

interface FoodAPI {
    //https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    //BASE_URL -> https://raw.githubusercontent.com/
    //atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json

    @GET("atilsamancioglu/BTK20-JSONVeriSeti/master/besinler.json")
    fun getFood(): Single<List<Food>>
}