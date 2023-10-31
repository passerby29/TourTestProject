package dev.passerby.tourtestproject.domain.models

data class RoomModel(
    val countTourist: Int,
    val date: Date,
    val duration: DurationRoom,
    val id: Int,
    val image: BaseImageModel,
    val price: PriceRoom,
    val title: String,
    val type: String
)

data class Date(
    val typeDate: String
)

data class DurationRoom(
    val night: Int
)

data class PriceRoom(
    val currency: String,
    val discount: Any?,
    val factPrice: Int,
    val price: Int,
    val typePrice: String
)
