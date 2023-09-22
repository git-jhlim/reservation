package com.exam.backoffice.application.lecture.model

import domain.lecture.entity.Lecture
import java.time.LocalDateTime

data class LectureCreateModel (
    val lecturer: String,
    val lectureRoom: String,
    val capacity: Int,
    val startDateTime: LocalDateTime,
    val summary: String,
) {
    fun toLecture() = Lecture(
        lecturer = lecturer,
        lectureRoom = lectureRoom,
        capacity = capacity,
        startDateTime = startDateTime,
        summary = summary,
    )
}