package com.exam.backoffice.application.lecture

import com.exam.backoffice.application.lecture.model.LectureCreateModel
import com.exam.backoffice.application.lecture.model.LectureInfo
import com.exam.backoffice.application.lecture.model.LectureSearchModel
import common.PageResponse
import domain.lecture.exception.LectureNotFoundException
import domain.lecture.repository.LectureRepository
import domain.reservation.repository.ReservationRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    private val reservationRepository: ReservationRepository,
    ) {
    @Transactional
    suspend fun create(createModel: LectureCreateModel) {
        lectureRepository.save(createModel.toLecture())
    }
    @Transactional(readOnly = true)
    suspend fun getLectures(searchModel: LectureSearchModel): PageResponse<LectureInfo> {
        return lectureRepository.getAllLectures(searchModel.toLectureSearchParams())
            .let { results ->  PageResponse.convert(results){ LectureInfo.of(it) } }
    }
    
    @Transactional(readOnly = true)
    suspend fun getAudiences(lectureNo: Int): List<String> {
        if(lectureRepository.existLecture(lectureNo)) {
            return reservationRepository.findByLectureNo(lectureNo)
                .map { it.employeeId }
        } else throw LectureNotFoundException()
    }
}