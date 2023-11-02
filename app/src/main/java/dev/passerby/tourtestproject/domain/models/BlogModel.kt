package dev.passerby.tourtestproject.domain.models

data class BlogModel(
    val blogList: List<BlogItem>,
){
    data class BlogItem(
        val date: BaseDateModel,
        val id: Int,
        val image: BaseImageModel,
        val like: Int,
        val subtitle: String,
        val title: String,
        val view: Int
    )
}