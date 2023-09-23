package com.exam.front.application.reservation

import com.exam.front.application.reservation.model.ReservationCreateModel
import com.exam.front.application.reservation.model.ReservationInfo
import com.exam.front.application.reservation.model.ReservationSearchModel
import common.PageResponse
import domain.lecture.repository.LectureRepository
import domain.reservation.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.RuntimeException

@Service
class ReservationService(
    private val reservationRepository: ReservationRepository,
    private val lectureRepository: LectureRepository,
) {
    @Transactional
    suspend fun reserveLecture(createModel: ReservationCreateModel) {
        if(!lectureRepository.existLecture(createModel.lectureNo))
            throw RuntimeException("유효하지 않은 강의정보 입니다.")

        val reservation = reservationRepository.findByLectureNoAndEmployeeId(
            lectureNo = createModel.lectureNo,
            employeeId = createModel.employeeId,
        )

        if(reservation == null) {
            reservationRepository.save(createModel.toReservation())
        } else throw RuntimeException("이미 강의 신청한 내역이 있습니다.")
    }

    @Transactional(readOnly = true)
    suspend fun getAllRigisteredLectures(searchModel: ReservationSearchModel): PageResponse<ReservationInfo> {
        return reservationRepository.findAllRegisteredLecturesBy(searchModel.toReservationSearchParams())
            .let { pageResponse ->
                PageResponse.convert(pageResponse) { ReservationInfo.of(it) }
            }
    }
}