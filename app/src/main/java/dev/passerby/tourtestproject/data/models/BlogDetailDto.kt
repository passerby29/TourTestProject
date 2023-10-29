package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class BlogDetailDto(
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