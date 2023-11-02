package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.ToursDto
import dev.passerby.tourtestproject.domain.models.TourModel

class ToursContentMapper {

    fun mapDtoToEntity(dto: ToursDto) = TourModel(
        toursContent = mapDtoToursToEntityTours(dto.toursContent),
        error = dto.error,
        success = dto.success,
        time = dto.time
    )

    private fun mapDtoToursToEntityTours(dtoList: List<ToursDto.TourItem>): List<TourModel.TourItem> {
        return dtoList.map {
            mapDtoItemToEntityItem(it)
        }
    }

    private fun mapDtoItemToEntityItem(dtoItem: ToursDto.TourItem) = TourModel.TourItem(
        date = mapDtoDateToEntityDate(dtoItem.date),
        duration = mapDtoDurationToEntityDuration(dtoItem.duration),
        home = mapDtoHomeToEntityHome(dtoItem.home),
        id = dtoItem.id,
        image = mapDtoImageToEntityImage(dtoItem.image),
        location = dtoItem.location,
        price = mapDtoPriceToEntityPrice(dtoItem.price),
        title = dtoItem.title,
        url = dtoItem.url,
    )

    private fun mapDtoDurationToEntityDuration(dtoDuration: ToursDto.TourItem.Duration) =
        TourModel.TourItem.Duration(
            day = dtoDuration.day,
            night = dtoDuration.night,
        )

    private fun mapDtoHomeToEntityHome(dtoHome: ToursDto.TourItem.Home) = TourModel.TourItem.Home(
        base = mapDtoBaseToEntityBase(dtoHome.base),
        id = dtoHome.id,
        image = mapDtoImageToEntityImage(dtoHome.image),
        name = dtoHome.name,
        night = dtoHome.night,
        type = dtoHome.type,
        url = dtoHome.url
    )

    private fun mapDtoBaseToEntityBase(dtoBase: ToursDto.TourItem.Home.Base) =
        TourModel.TourItem.Home.Base(
            id = dtoBase.id,
            name = dtoBase.name,
            url = dtoBase.url
        )

    private fun mapDtoPriceToEntityPrice(dtoPrice: ToursDto.TourItem.Price) =
        TourModel.TourItem.Price(
            currency = dtoPrice.currency,
            factPrice = dtoPrice.factPrice,
            price = dtoPrice.price,
            typePrice = dtoPrice.typePrice
        )
}