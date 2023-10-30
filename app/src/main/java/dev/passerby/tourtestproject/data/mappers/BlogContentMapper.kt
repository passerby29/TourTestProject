package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.BaseDateDto
import dev.passerby.tourtestproject.data.models.BaseImageDto
import dev.passerby.tourtestproject.data.models.BlogDto
import dev.passerby.tourtestproject.data.models.BlogItem
import dev.passerby.tourtestproject.domain.models.BaseDateModel
import dev.passerby.tourtestproject.domain.models.BaseImageModel
import dev.passerby.tourtestproject.domain.models.BlogModel

class BlogContentMapper {
    fun mapDtoToEntity(dto: BlogDto): BlogModel {
        val entityList = mutableListOf<dev.passerby.tourtestproject.domain.models.BlogItem>()
        dto.blogList.map {
            entityList.add(mapDtoContentToEntityContent(it))
        }
        return BlogModel(entityList)
    }

    fun mapDtoContentToEntityContent(dtoBlog: BlogItem) =
        dev.passerby.tourtestproject.domain.models.BlogItem(
            date = mapDtoDateToEntityDate(dtoBlog.date),
            id = dtoBlog.id,
            image = mapDtoImageToEntityImage(dtoBlog.image),
            like = dtoBlog.like,
            subtitle = dtoBlog.subtitle,
            title = dtoBlog.title,
            view = dtoBlog.view,
        )

    private fun mapDtoDateToEntityDate(dtoDate: BaseDateDto) = BaseDateModel(
        date = dtoDate.date,
        typeDate = dtoDate.typeDate
    )

    private fun mapDtoImageToEntityImage(dtoImage: BaseImageDto) = BaseImageModel(
        lg = dtoImage.lg,
        md = dtoImage.md,
        sm = dtoImage.sm
    )
}
