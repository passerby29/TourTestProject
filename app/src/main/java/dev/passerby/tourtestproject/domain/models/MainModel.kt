package dev.passerby.tourtestproject.domain.models

data class MainModel(
    val mainInfo: MainInfo,
    val error: Any?,
    val success: Boolean,
    val time: String
)

data class MainInfo(
    val buttons: List<Button>,
    val content: List<Content>
)

data class Button(
    val color: String,
    val icon: String,
    val title: String,
    val type: String,
    val url: String
)

data class Content(
    val template: Template,
    val title: String,
    val url: String
)

data class Template(
    val card: String,
    val direction: String,
    val objectTemplate: String,
    val size: String
)