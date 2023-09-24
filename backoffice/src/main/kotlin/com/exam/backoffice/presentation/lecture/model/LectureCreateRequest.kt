package com.exam.backoffice.presentation.lecture.model

import com.exam.backoffice.application.lecture.model.LectureCreateModel
import exception.InvalidArgumentException
import exception.InvalidFieldValueException
import java.time.LocalDate

data class LectureCreateRequest(
    val lecturer: String,
    val lectureRoom: String,
    val capacity: Int,
    val startDateTime: LectureDateTime,
    val description: String,
) {
    data class LectureDateTime (
        val date: LocalDate,
        val hour: Int,
        val minute: Int,
    ) {
        fun toLocalDateTime() = date.atTime(hour, minute)
        
        fun validate() {
            if(hour !in 0..23)
                throw InvalidArgumentException("startDateTime.hour")
            if(minute !in 0..59)
                throw InvalidArgumentException("startDateTime.minute")
        }
    }
    fun toLectureCreateModel() = LectureCreateModel(
        lecturer = lecturer,
        lectureRoom = lectureRoom,
        capacity = capacity,
        startDateTime = startDateTime.toLocalDateTime(),
        description = description,
    )
    
    fun validate() {
        if(MAX_LECTURER_LENGTH < lecturer.length)
            throw InvalidFieldValueException(arrayOf("lecturer", MAX_LECTURER_LENGTH))
            
        if(MAX_LECTURE_ROOM_LENGTH < lectureRoom.length)
            throw InvalidFieldValueException(arrayOf("lectureRoom", MAX_LECTURE_ROOM_LENGTH))
            
        if (MAX_DESCRIPTION_LENGTH < description.length)
            throw InvalidFieldValueException(arrayOf("description", MAX_DESCRIPTION_LENGTH))
        
        startDateTime.validate()
    }
    
    companion object {
        private const val MAX_LECTURER_LENGTH = 50
        private const val MAX_LECTURE_ROOM_LENGTH = 30
        private const val MAX_DESCRIPTION_LENGTH = 500
    }
}