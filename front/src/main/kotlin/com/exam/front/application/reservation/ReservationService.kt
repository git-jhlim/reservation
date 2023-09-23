package com.exam.front.application.reservation

import com.exam.front.application.reservation.model.ReservationCreateModel
import com.exam.front.application.reservation.model.ReservationInfo
import com.exam.front.application.reservation.model.ReservationSearchModel
import common.PageResponse
import domain.lecture.exception.LectureNotFoundException
import domain.lecture.repository.LectureRepository
import domain.reservation.exception.AlreadyReservedException
import domain.reservation.exception.ReservationNotFoundException
import domain.reservation.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val lectureRepository: LectureRepository,
) {
    @Transactional
    suspend fun reserveLecture(createModel: ReservationCreateModel) {
        if(!lectureRepository.existLecture(createModel.lectureNo))
            throw LectureNotFoundException()

        val reservation = reservationRepository.findByLectureNoAndEmployeeId(
            lectureNo = createModel.lectureNo,
            employeeId = createModel.employeeId,
        )

        if(reservation == null) {
            reservationRepository.save(createModel.toReservation())
        } else throw AlreadyReservedException()
    }

    @Transactional(readOnly = true)
    suspend fun getAllRigisteredLectures(searchModel: ReservationSearchModel): PageResponse<ReservationInfo> {
        return reservationRepository.findAllRegisteredLecturesBy(searchModel.toReservationSearchParams())
            .let { pageResponse ->
                PageResponse.convert(pageResponse) { ReservationInfo.of(it) }
            }
    }
    
    @Transactional(readOnly = true)
    suspend fun cancelReservation(employeeId: String, reservationNo: Int) {
        val reservation = reservationRepository.findByNoAndEmployeeId(reservationNo, employeeId)
        
        if(reservation == null) {
            throw ReservationNotFoundException()
        } else {
            reservation.cancel()
        }
    }
}