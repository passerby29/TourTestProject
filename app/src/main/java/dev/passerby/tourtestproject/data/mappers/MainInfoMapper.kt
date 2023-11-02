package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.MainDto
import dev.passerby.tourtestproject.domain.models.MainModel

class MainInfoMapper {
    fun mapDtoToEntity(dto: MainDto) = MainModel(
        mainInfo = mapDtoInfoToEntityInfo(dto.mainInfo),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoInfoToEntityInfo(dtoInfo: MainDto.MainInfo) = MainModel.MainInfo(
        buttons = mapDtoButtonsToEntityButtons(dtoInfo.buttons),
        content = mapDtoContentListToEntityContentList(dtoInfo.content),
    )

    private fun mapDtoButtonsToEntityButtons(dtoButtons: List<MainDto.MainInfo.Button>): List<MainModel.MainInfo.Button> {
        return dtoButtons.map { mapDtoButtonToEntityButton(it) }
    }

    private fun mapDtoButtonToEntityButton(dtoButton: MainDto.MainInfo.Button) =
        MainModel.MainInfo.Button(
            color = dtoButton.color,
            icon = dtoButton.icon,
            title = dtoButton.title,
            type = dtoButton.type,
            url = dtoButton.url
        )

    private fun mapDtoContentListToEntityContentList(dtoContentList: List<MainDto.MainInfo.Content>):
            List<MainModel.MainInfo.Content> {
        return dtoContentList.map { mapDtoContentToEntityContent(it) }
    }

    private fun mapDtoContentToEntityContent(dtoContent: MainDto.MainInfo.Content) =
        MainModel.MainInfo.Content(
            template = mapDtoTemplateToEntityTemplate(dtoContent.template),
            title = dtoContent.title,
            url = dtoContent.url,
        )

    private fun mapDtoTemplateToEntityTemplate(dtoTemplate: MainDto.MainInfo.Content.Template) =
        MainModel.MainInfo.Content.Template(
            card = dtoTemplate.card,
            direction = dtoTemplate.direction,
            objectTemplate = dtoTemplate.objectTemplate,
            size = dtoTemplate.size,
        )
}