package dev.passerby.tourtestproject.domain.models

data class FunModel(
    val funContent: List<FunItem>,
    val error: Any?,
    val success: Boolean,
    val time: String
)

data class FunItem(
    val id: Int,
    val image: BaseImageModel,
    val subtitle: String,
    val title: String
)