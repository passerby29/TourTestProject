package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class BlogDto(
    @SerializedName("data")
    val blogList: List<BlogItem>,
)

data class BlogItem(
    @SerializedName("date")
    val date: BaseDateDto,
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
    @SerializedName("view")
    val view: Int
)