package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.BlogDetailDto
import dev.passerby.tourtestproject.domain.models.BlogDetailModel

class BlogDetailMapper {

    fun mapDtoToEntity(dto: BlogDetailDto) = BlogDetailModel(
        blogDetail = mapDtoDetailToEntityDetail(dto.blogDetail),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoDetailToEntityDetail(dtoDetail: BlogDetailDto.BlogDetail) =
        BlogDetailModel.BlogDetail(
            content = dtoDetail.content,
            date = dtoDetail.date,
            id = dtoDetail.id,
            image = mapDtoImageToEntityImage(dtoDetail.image),
            like = dtoDetail.like,
            subtitle = dtoDetail.subtitle,
            title = dtoDetail.title,
            url = dtoDetail.url,
        )
}