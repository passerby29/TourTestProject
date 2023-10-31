package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class RoomsDto(
    @SerializedName("data")
    val roomsContent: List<RoomItem>,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("time")
    val time: String
) {
    data class RoomItem(
        @SerializedName("countTourist")
        val countTourist: Int,
        @SerializedName("date")
        val date: Date,
        @SerializedName("duration")
        val duration: Duration,
        @SerializedName("id")
        val id: Int,
        @SerializedName("image")
        val image: BaseImageDto,
        @SerializedName("price")
        val price: Price,
        @SerializedName("title")
        val title: String,
        @SerializedName("type")
        val type: String
    ) {
        data class Date(
            @SerializedName("typeDate")
            val typeDate: String
        )

        data class Duration(
            @SerializedName("night")
            val night: Int
        )

        data class Price(
            @SerializedName("currency")
            val currency: String,
            @SerializedName("discount")
            val discount: Any?,
            @SerializedName("factPrice")
            val factPrice: Int,
            @SerializedName("price")
            val price: Int,
            @SerializedName("typePrice")
            val typePrice: String
        )
    }
}