package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.BlogDto
import dev.passerby.tourtestproject.domain.models.BlogModel

class BlogContentMapper {
    fun mapDtoContentToEntityContent(dtoBlog: BlogDto.BlogItem) = BlogModel.BlogItem(
        date = mapDtoDateToEntityDate(dtoBlog.date),
        id = dtoBlog.id,
        image = mapDtoImageToEntityImage(dtoBlog.image),
        like = dtoBlog.like,
        subtitle = dtoBlog.subtitle,
        title = dtoBlog.title,
        view = dtoBlog.view,
    )
}
