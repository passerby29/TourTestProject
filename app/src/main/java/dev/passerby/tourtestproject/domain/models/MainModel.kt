package dev.passerby.tourtestproject.domain.models

data class MainModel(
    val main: List<Main>,
)

data class Main(
    val buttons: List<Button>,
    val content: List<Content>
)

data class Button(
    val icon: String,
    val color: String,
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
