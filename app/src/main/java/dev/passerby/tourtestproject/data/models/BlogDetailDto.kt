package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class BlogDetailDto(
    @SerializedName("data")
    val blogDetail: BlogDetail,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("time")
    val time: String
)

data class BlogDetail(
    @SerializedName("content")
    val content: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: BaseImageDto,
    @SerializedName("like")
    val like: Int,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)