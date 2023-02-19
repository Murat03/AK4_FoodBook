package com.muratipek.ak4_foodbook.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Food(
    @ColumnInfo(name = "name")
    @SerializedName("isim")
    val name: String?,
    @ColumnInfo(name = "calori")
    @SerializedName("kalori")
    val calori: String?,
    @ColumnInfo(name = "carbohydrate")
    @SerializedName("karbonhidrat")
    val carbohydrate: String?,
    @ColumnInfo(name = "protein")
    @SerializedName("protein")
    val protein: String?,
    @ColumnInfo(name = "fat")
    @SerializedName("yag")
    val fat: String?,
    @ColumnInfo(name = "image")
    @SerializedName("gorsel")
    val image: String?
    ) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}