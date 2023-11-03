package dev.passerby.tourtestproject.data.mappers

import dev.passerby.tourtestproject.data.models.RoomsDto
import dev.passerby.tourtestproject.domain.models.RoomsModel

class RoomsContentMapper {

    fun mapDtoToEntity(dto: RoomsDto) = RoomsModel(
        roomsContent = mapDtoRoomsToEntityRooms(dto.roomsContent),
        error = dto.error,
        success = dto.success,
        time = dto.time,
    )

    private fun mapDtoRoomsToEntityRooms(dtoList: List<RoomsDto.RoomItem>): List<RoomsModel.RoomItem> {
        return dtoList.map {
            mapDtoItemToEntityItem(it)
        }
    }

    private fun mapDtoItemToEntityItem(dtoItem: RoomsDto.RoomItem) = RoomsModel.RoomItem(
        countTourist = dtoItem.countTourist,
        date = mapDtoDateToEntityDate(dtoItem.date),
        duration = mapDtoDurationToEntityDuration(dtoItem.duration),
        id = dtoItem.id,
        image = mapDtoImageToEntityImage(dtoItem.image),
        price = mapDtoPriceToEntityPrice(dtoItem.price),
        title = dtoItem.title,
        type = dtoItem.type,
    )

    private fun mapDtoDateToEntityDate(dtoDate: RoomsDto.RoomItem.Date) = RoomsModel.RoomItem.Date(
        typeDate = dtoDate.typeDate
    )

    private fun mapDtoDurationToEntityDuration(dtoDuration: RoomsDto.RoomItem.Duration) =
        RoomsModel.RoomItem.Duration(
            night = dtoDuration.night
        )

    private fun mapDtoPriceToEntityPrice(dtoPrice: RoomsDto.RoomItem.Price) =
        RoomsModel.RoomItem.Price(
            currency = dtoPrice.currency,
            discount = dtoPrice.discount,
            factPrice = dtoPrice.factPrice,
            price = dtoPrice.price,
            typePrice = dtoPrice.typePrice,
        )
}