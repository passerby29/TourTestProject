package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.FunDto
import dev.passerby.tourtestproject.data.models.FunItem
import dev.passerby.tourtestproject.data.models.RoomsDto
import dev.passerby.tourtestproject.domain.models.FunModel
import dev.passerby.tourtestproject.domain.models.RoomsModel

class FunContentMapper {

    fun mapDtoToEntity(dto: FunDto) = FunModel(
        funContent = mapDtoFunListToEntityFunList(dto.funContent),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoFunListToEntityFunList(dtoList: List<FunItem>):
            List<dev.passerby.tourtestproject.domain.models.FunItem> {
        return dtoList.map {
            mapDtoItemToEntityItem(it)
        }
    }

    private fun mapDtoItemToEntityItem(dtoItem: FunItem) =
        dev.passerby.tourtestproject.domain.models.FunItem(
            id = dtoItem.id,
            image = mapDtoImageToEntityImage(dtoItem.image),
            subtitle = dtoItem.subtitle,
            title = dtoItem.title,
        )
}