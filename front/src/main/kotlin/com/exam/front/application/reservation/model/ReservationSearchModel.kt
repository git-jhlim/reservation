package com.exam.front.application.reservation.model

import domain.reservation.model.ReservationSearchParams

data class ReservationSearchModel (
    val page: Int,
    val size: Int,
    val employeeId: String,
) {
    fun toReservationSearchParams() = ReservationSearchParams (page, size, employeeId)
}