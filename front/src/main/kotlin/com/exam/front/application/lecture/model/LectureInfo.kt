package com.exam.front.application.lecture.model

import domain.lecture.entity.Lecture
import java.time.LocalDateTime

data class LectureInfo (
    val no: Int,
    val lecturer: String,
    val capacity: Int,
    val lectureRoom: String,
    val description: String,
    val date: LocalDateTime,
) {
    companion object {
        fun of(lecture: Lecture) = LectureInfo(
            no = lecture.no,
            lecturer = lecture.lecturer,
            capacity = lecture.capacity,
            lectureRoom = lecture.lectureRoom,
            description = lecture.description,
            date = lecture.startDateTime,
        )
    }
}
