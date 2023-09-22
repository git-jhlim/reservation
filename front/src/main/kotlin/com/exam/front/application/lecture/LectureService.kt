package com.exam.front.application.lecture

import com.exam.front.application.lecture.model.SearchModel
import com.exam.front.presentation.lecture.model.LectureInfo
import common.PageResponse
import domain.lecture.repository.LectureRepository
import org.springframework.stereotype.Service

@Service
class LectureService(private val lectureRepository: LectureRepository){
    fun search(searchModel: SearchModel): PageResponse<LectureInfo> {
        return lectureRepository.getAvailableLectures(searchModel.toSearchParams())
            .let {
                PageResponse.convert(it) {
                    LectureInfo.of(it)
                }
            }
    }
}