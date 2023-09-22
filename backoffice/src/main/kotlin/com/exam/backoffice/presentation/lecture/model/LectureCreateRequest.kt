package com.exam.backoffice.presentation.lecture.model

import java.time.LocalDateTime

data class LectureCreateRequest(
    val lecturer: String,
    val lectureRoom: String,
    val capacity: Int,
    val startDateTime: LocalDateTime,
    val summary: String,
)