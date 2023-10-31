package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.Button
import dev.passerby.tourtestproject.data.models.Content
import dev.passerby.tourtestproject.data.models.MainDto
import dev.passerby.tourtestproject.data.models.MainInfo
import dev.passerby.tourtestproject.data.models.Template
import dev.passerby.tourtestproject.domain.models.MainModel

class MainInfoMapper {
    fun mapDtoToEntity(dto: MainDto) = MainModel(
        mainInfo = mapDtoInfoToEntityInfo(dto.mainInfo),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoInfoToEntityInfo(dtoInfo: MainInfo) =
        dev.passerby.tourtestproject.domain.models.MainInfo(
            buttons = mapDtoButtonsToEntityButtons(dtoInfo.buttons),
            content = mapDtoContentListToEntityContentList(dtoInfo.content),
        )

    private fun mapDtoButtonsToEntityButtons(dtoButtons: List<Button>):
            List<dev.passerby.tourtestproject.domain.models.Button> {
        return dtoButtons.map { mapDtoButtonToEntityButton(it) }
    }

    private fun mapDtoButtonToEntityButton(dtoButton: Button) =
        dev.passerby.tourtestproject.domain.models.Button(
            color = dtoButton.color,
            icon = dtoButton.icon,
            title = dtoButton.title,
            type = dtoButton.type,
            url = dtoButton.url
        )

    private fun mapDtoContentListToEntityContentList(dtoContentList: List<Content>):
            List<dev.passerby.tourtestproject.domain.models.Content> {
        return dtoContentList.map { mapDtoContentToEntityContent(it) }
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