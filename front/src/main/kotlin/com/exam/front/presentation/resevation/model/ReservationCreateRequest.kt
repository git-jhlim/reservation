package com.exam.front.presentation.resevation.model

import com.exam.front.application.reservation.model.ReservationCreateModel
import common.constant.CommonConstant.EMPLOYEE_ID_LENGTH
import exception.InvalidArgumentException

data class ReservationCreateRequest (
    val employeeId: String,
    val lectureNo: Int,
) {
    fun toReservationCreateModel() = ReservationCreateModel(employeeId, lectureNo)
    
    fun validate() {
        if(employeeId.length != EMPLOYEE_ID_LENGTH)
            throw InvalidArgumentException("employeeId")
    }
}