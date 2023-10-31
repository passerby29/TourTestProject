package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class MainDto(
    @SerializedName("data")
    val mainInfo: MainInfo,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("time")
    val time: String
)

data class MainInfo(
    @SerializedName("buttons")
    val buttons: List<Button>,
    @SerializedName("content")
    val content: List<Content>
)

data class Button(
    @SerializedName("color")
    val color: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)

data class Content(
    @SerializedName("template")
    val template: Template,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)

data class Template(
    @SerializedName("card")
    val card: String,
    @SerializedName("direction")
    val direction: String,
    @SerializedName("object")
    val objectTemplate: String,
    @SerializedName("size")
    val size: String
)