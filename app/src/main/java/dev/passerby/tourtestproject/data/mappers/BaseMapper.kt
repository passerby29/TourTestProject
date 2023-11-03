package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.BaseDateDto
import dev.passerby.tourtestproject.data.models.BaseImageDto
import dev.passerby.tourtestproject.domain.models.BaseDateModel
import dev.passerby.tourtestproject.domain.models.BaseImageModel

fun mapDtoDateToEntityDate(dtoDate: BaseDateDto) = BaseDateModel(
    date = dtoDate.date,
    typeDate = dtoDate.typeDate
)

fun mapDtoImageToEntityImage(dtoImage: BaseImageDto) = BaseImageModel(
    lg = dtoImage.lg,
    md = dtoImage.md,
    sm = dtoImage.sm
)