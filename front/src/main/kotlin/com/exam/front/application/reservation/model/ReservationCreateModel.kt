package com.exam.front.application.reservation.model

import domain.reservation.entity.Reservation


data class ReservationCreateModel (
    val employeeId: String,
    val lectureNo: Int,
) {
    fun toReservation() = Reservation(
        lectureNo = lectureNo,
        employeeId = employeeId,
    )
}