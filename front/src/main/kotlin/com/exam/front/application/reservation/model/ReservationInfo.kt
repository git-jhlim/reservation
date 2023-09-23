package com.exam.front.application.reservation.model

import domain.reservation.model.RegisteredLecture
import java.time.LocalDateTime

data class ReservationInfo (
    val no: Int,
    val registerDate: LocalDateTime,
    val lecture: Lecture,
) {
    data class Lecture(
        val lecturer: String,
        val lectureRoom: String,
        val date: LocalDateTime,
        val description: String,
    )


    companion object {
        fun of(registered: RegisteredLecture) = ReservationInfo(
            no = registered.reservationNo,
            registerDate = registered.registerDate,
            lecture = Lecture(
                lecturer = registered.lecturer,
                lectureRoom = registered.lectureRoom,
                date = registered.lectureDate,
                description = registered.description,
            )
        )
    }
}