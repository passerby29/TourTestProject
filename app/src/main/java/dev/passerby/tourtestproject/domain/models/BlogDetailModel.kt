package dev.passerby.tourtestproject.domain.models

data class BlogDetailModel(
    val content: String,
    val date: String,
    val id: Int,
    val image: BaseImageModel,
    val like: Int,
    val subtitle: String,
    val title: String,
    val url: String,
)