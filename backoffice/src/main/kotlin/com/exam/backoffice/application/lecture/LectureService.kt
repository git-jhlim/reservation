package com.exam.backoffice.application.lecture

import com.exam.backoffice.application.lecture.model.LectureCreateModel
import com.exam.backoffice.application.lecture.model.LectureInfo
import domain.lecture.repository.LectureRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    ) {
    @Transactional
    suspend fun create(createModel: LectureCreateModel) {
        lectureRepository.save(createModel.toLecture())
    }
    @Transactional(readOnly = true)
    suspend fun getAll(): List<LectureInfo> {

        return lectureRepository.findAll()
            .map { LectureInfo.of(it) }
    }
}