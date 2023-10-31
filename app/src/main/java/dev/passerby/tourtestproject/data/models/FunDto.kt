package dev.passerby.tourtestproject.data.models


import com.google.gson.annotations.SerializedName

data class FunDto(
    @SerializedName("data")
    val funContent: List<FunItem>,
    @SerializedName("error")
    val error: Any?,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("time")
    val time: String
)

data class FunItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: BaseImageDto,
    @SerializedName("subtitle")
    val subtitle: String,
    @SerializedName("title")
    val title: String
)