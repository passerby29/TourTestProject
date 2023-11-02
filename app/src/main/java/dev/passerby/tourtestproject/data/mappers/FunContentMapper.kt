package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.FunDto
import dev.passerby.tourtestproject.domain.models.FunModel

class FunContentMapper {

    fun mapDtoToEntity(dto: FunDto) = FunModel(
        funContent = mapDtoFunListToEntityFunList(dto.funContent),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoFunListToEntityFunList(dtoList: List<FunDto.FunItem>): List<FunModel.FunItem> {
        return dtoList.map {
            mapDtoItemToEntityItem(it)
        }
    }

    private fun mapDtoItemToEntityItem(dtoItem: FunDto.FunItem) = FunModel.FunItem(
        id = dtoItem.id,
        image = mapDtoImageToEntityImage(dtoItem.image),
        subtitle = dtoItem.subtitle,
        title = dtoItem.title,
    )
}