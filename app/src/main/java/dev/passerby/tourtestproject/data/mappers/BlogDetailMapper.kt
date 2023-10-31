package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.BaseImageDto
import dev.passerby.tourtestproject.data.models.BlogDetail
import dev.passerby.tourtestproject.data.models.BlogDetailDto
import dev.passerby.tourtestproject.domain.models.BaseImageModel
import dev.passerby.tourtestproject.domain.models.BlogDetailModel

class BlogDetailMapper {

    fun mapDtoToEntity(dto: BlogDetailDto) = BlogDetailModel(
        blogDetail = mapDtoDetailToEntityDetail(dto.blogDetail),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoDetailToEntityDetail(dtoDetail: BlogDetail) =
        dev.passerby.tourtestproject.domain.models.BlogDetail(
            content = dtoDetail.content,
            date = dtoDetail.date,
            id = dtoDetail.id,
            image = mapDtoImageToEntityImage(dtoDetail.image),
            like = dtoDetail.like,
            subtitle = dtoDetail.subtitle,
            title = dtoDetail.title,
            url = dtoDetail.url,
        )

    private fun mapDtoImageToEntityImage(dtoImage: BaseImageDto) = BaseImageModel(
        lg = dtoImage.lg,
        md = dtoImage.md,
        sm = dtoImage.sm
    )
}