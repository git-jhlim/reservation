package com.exam.backoffice.application.lecture.model

import domain.lecture.entity.Lecture
import java.time.LocalDateTime

data class LectureInfo (
    val no: Int,
    val lecturer: String,
    val lectureRoom: String,
    val date: LocalDateTime,
) {
    companion object {
        fun of(lecture: Lecture) = LectureInfo(
            no = lecture.no,
            lecturer = lecture.lecturer,
            lectureRoom = lecture.lectureRoom,
            date = lecture.startDateTime,
        )
    }
}