package com.exam.front.application.lecture

import com.exam.front.application.lecture.model.LectureInfo
import com.exam.front.application.lecture.model.LectureSearchModel
import common.PageResponse
import domain.lecture.repository.LectureRepository
import org.springframework.stereotype.Service

@Service
class LectureService(private val lectureRepository: LectureRepository){
    fun search(searchModel: LectureSearchModel): PageResponse<LectureInfo> {
        return lectureRepository.getAvailableLectures(searchModel.toLectureSearchParams())
            .let { result ->
                PageResponse.convert(result) {  LectureInfo.of(it) }
            }
    }
}