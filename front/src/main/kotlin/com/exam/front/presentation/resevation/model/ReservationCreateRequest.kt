package com.exam.front.presentation.resevation.model

import com.exam.front.application.reservation.model.ReservationCreateModel

data class ReservationCreateRequest (
    val employeeId: String,
    val lectureNo: Int,
) {
    fun toReservationCreateModel() = ReservationCreateModel(employeeId, lectureNo)
}