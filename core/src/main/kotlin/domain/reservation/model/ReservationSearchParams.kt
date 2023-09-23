package domain.reservation.model

import common.PageRequestModel

data class ReservationSearchParams (
    val page: Int,
    val size: Int,
    val employeeId: String,
): PageRequestModel(page, size)