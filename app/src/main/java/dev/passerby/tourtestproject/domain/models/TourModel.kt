package dev.passerby.tourtestproject.domain.models


data class TourModel(
    val toursContent: List<TourItem>,
    val error: Any?,
    val success: Boolean,
    val time: String
) {
    data class TourItem(
        val date: BaseDateModel,
        val duration: Duration,
        val home: Home,
        val id: Int,
        val image: BaseImageModel,
        val location: String,
        val price: Price,
        val title: String,
        val url: String
    ) {

        data class Duration(
            val day: Int?,
            val night: Int
        )

        data class Home(
            val base: Base,
            val id: Int,
            val image: BaseImageModel,
            val name: String,
            val night: Int,
            val type: String,
            val url: String
        ) {
            data class Base(
                val id: Int,
                val name: String,
                val url: String
            )
        }

        data class Price(
            val currency: String,
            val factPrice: Int,
            val price: Int,
            val typePrice: String
        )
    }
}