package dev.passerby.tourtestproject.domain.models

data class RoomsModel(
    val roomsContent: List<RoomItem>,
    val error: Any?,
    val success: Boolean,
    val time: String
) {
    data class RoomItem(
        val countTourist: Int,
        val date: Date,
        val duration: Duration,
        val id: Int,
        val image: BaseImageModel,
        val price: Price,
        val title: String,
        val type: String
    ) {
        data class Date(
            val typeDate: String
        )

        data class Duration(
            val night: Int
        )

        data class Price(
            val currency: String,
            val discount: Any?,
            val factPrice: Int,
            val price: Int,
            val typePrice: String
        )
    }
}
