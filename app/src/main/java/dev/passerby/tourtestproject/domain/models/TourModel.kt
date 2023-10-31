package dev.passerby.tourtestproject.domain.models


import com.google.gson.annotations.SerializedName

data class TourModel(
    @SerializedName("date")
    val date: BaseDateModel,
    @SerializedName("duration")
    val duration: DurationTour,
    @SerializedName("home")
    val home: Home,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: BaseImageModel,
    @SerializedName("location")
    val location: String,
    @SerializedName("price")
    val price: PriceTour,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

data class DurationTour(
    @SerializedName("day")
    val day: Int?,
    @SerializedName("night")
    val night: Int
)

data class Home(
    @SerializedName("base")
    val base: Base,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: BaseImageModel,
    @SerializedName("name")
    val name: String,
    @SerializedName("night")
    val night: Int,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

data class PriceTour(
    val currency: String,
    val factPrice: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("typePrice")
    val typePrice: String
)

data class Base(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)