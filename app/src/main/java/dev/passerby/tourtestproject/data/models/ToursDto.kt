package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class ToursDto(
    @SerializedName("data")
    val toursContent: List<TourItem>,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("time")
    val time: String
) {
    data class TourItem(
        @SerializedName("date")
        val date: BaseDateDto,
        @SerializedName("duration")
        val duration: Duration,
        @SerializedName("home")
        val home: Home,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: BaseImageDto,
        @SerializedName("location")
        val location: String,
        @SerializedName("price")
        val price: Price,
        @SerializedName("title")
        val title: String,
        @SerializedName("url")
        val url: String
    ) {

        data class Duration(
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
            val image: BaseImageDto,
            @SerializedName("name")
            val name: String,
            @SerializedName("night")
            val night: Int,
            @SerializedName("type")
            val type: String,
            @SerializedName("url")
            val url: String
        ) {
            data class Base(
                @SerializedName("id")
                val id: Int,
                @SerializedName("name")
                val name: String,
                @SerializedName("url")
                val url: String
            )
        }

        data class Price(
            @SerializedName("currency")
            val currency: String,
            @SerializedName("factPrice")
            val factPrice: Int,
            @SerializedName("price")
            val price: Int,
            @SerializedName("typePrice")
            val typePrice: String
        )
    }
}