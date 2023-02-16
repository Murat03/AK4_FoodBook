package com.muratipek.ak4_foodbook.model

import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("isim")
    val name: String?,
    @SerializedName("kalori")
    val calori: String?,
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,
    @SerializedName("protein")
    val protein: String?,
    @SerializedName("yag")
    val fat: String?,
    @SerializedName("gorsel")
    val image: String?
    ) {

}