package dev.passerby.tourtestproject.domain.models

data class BlogDetailModel(
    val blogDetail: BlogDetail,
    val error: Any?,
    val success: Boolean,
    val time: String
) {
    data class BlogDetail(
        val content: String,
        val date: String,
        val id: Int,
        val image: BaseImageModel,
        val like: Int,
        val subtitle: String,
        val title: String,
        val url: String
    )
}
