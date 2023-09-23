package domain.reservation.model

import com.querydsl.core.annotations.QueryProjection
import java.time.LocalDateTime

data class RegisteredLecture
@QueryProjection constructor (
    val reservationNo: Int,
    val lecturer: String,
    val lectureRoom: String,
    val description: String,
    val lectureDate: LocalDateTime,
    val registerDate: LocalDateTime,
)