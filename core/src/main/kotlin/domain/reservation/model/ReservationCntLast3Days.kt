package domain.reservation.model

import com.querydsl.core.annotations.QueryProjection

data class ReservationCntLast3Days
@QueryProjection constructor (
	val lectureNo: Int,
  	val cnt: Long,
)