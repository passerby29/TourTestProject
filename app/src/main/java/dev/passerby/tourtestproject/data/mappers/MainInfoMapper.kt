package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.Button
import dev.passerby.tourtestproject.data.models.Content
import dev.passerby.tourtestproject.data.models.MainDto
import dev.passerby.tourtestproject.data.models.MainInfo
import dev.passerby.tourtestproject.data.models.Template
import dev.passerby.tourtestproject.domain.models.MainModel

class MainInfoMapper {
    fun mapDtoToEntity(dto: MainDto) = MainModel(
        mainInfo = mapDtoInfoToEntityInfo(dto.mainInfo)
    )

    private fun mapDtoInfoToEntityInfo(dtoInfo: MainInfo) =
        dev.passerby.tourtestproject.domain.models.MainInfo(
            buttons = mapDtoButtonsToEntityButtons(dtoInfo.buttons),
            content = mapDtoContentsToEntityContents(dtoInfo.content)
        )

    private fun mapDtoButtonsToEntityButtons(dtoButtons: List<Button>): List<dev.passerby.tourtestproject.domain.models.Button> {
        val entityButtons = mutableListOf<dev.passerby.tourtestproject.domain.models.Button>()
        dtoButtons.map {
            entityButtons.add(mapDtoButtonToEntityButton(it))
        }
        return entityButtons
    }

    private fun mapDtoButtonToEntityButton(dtoButton: Button) =
        dev.passerby.tourtestproject.domain.models.Button(
            icon = dtoButton.icon,
            color = dtoButton.color,
            title = dtoButton.title,
            type = dtoButton.type,
            url = dtoButton.url,
        )

    private fun mapDtoContentsToEntityContents(dtoContents: List<Content>): List<dev.passerby.tourtestproject.domain.models.Content> {
        val entityContents = mutableListOf<dev.passerby.tourtestproject.domain.models.Content>()
        dtoContents.map {
            entityContents.add(mapDtoContentToEntityContent(it))
        }
        return entityContents
    }

    private fun mapDtoContentToEntityContent(dtoContent: Content) =
        dev.passerby.tourtestproject.domain.models.Content(
            template = mapDtoTemplateToEntityTemplate(dtoContent.template),
            title = dtoContent.title,
            url = dtoContent.url,
        )

    private fun mapDtoTemplateToEntityTemplate(dtoTemplate: Template) =
        dev.passerby.tourtestproject.domain.models.Template(
            card = dtoTemplate.card,
            direction = dtoTemplate.direction,
            objectTemplate = dtoTemplate.objectTemplate,
            size = dtoTemplate.size,
        )
}