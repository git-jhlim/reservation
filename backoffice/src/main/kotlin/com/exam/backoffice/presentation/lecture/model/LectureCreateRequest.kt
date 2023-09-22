package com.exam.backoffice.presentation.lecture.model

import com.exam.backoffice.application.lecture.model.LectureCreateModel
import java.time.LocalDate
import java.time.LocalDateTime

data class LectureCreateRequest(
    val lecturer: String,
    val lectureRoom: String,
    val capacity: Int,
    val startDateTime: LectureDateTime,
    val summary: String,
) {
    data class LectureDateTime (
        val date: LocalDate,
        val hour: Int,
        val minute: Int,
    ) {
        fun toLocalDateTime() = date.atTime(hour, minute)
    }
    fun toLectureCreateModel() = LectureCreateModel(
        lecturer = lecturer,
        lectureRoom = lectureRoom,
        capacity = capacity,
        startDateTime = startDateTime.toLocalDateTime(),
        summary = summary,
    )
}