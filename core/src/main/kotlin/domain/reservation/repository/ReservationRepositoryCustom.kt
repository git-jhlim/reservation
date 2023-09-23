package domain.reservation.repository

import common.PageResponse
import domain.reservation.model.RegisteredLecture
import domain.reservation.model.ReservationCntLast3Days
import domain.reservation.model.ReservationSearchParams

interface ReservationRepositoryCustom {
    fun findAllRegisteredLecturesBy(searchParams: ReservationSearchParams): PageResponse<RegisteredLecture>
    fun getCountGroupByLectureNo(): List<ReservationCntLast3Days>
}