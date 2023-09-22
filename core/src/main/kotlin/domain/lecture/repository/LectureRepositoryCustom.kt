package domain.lecture.repository

import common.PageResponse
import domain.lecture.entity.Lecture
import domain.lecture.model.SearchParams

interface LectureRepositoryCustom {
    fun getAvailableLectures(searchParams: SearchParams): PageResponse<Lecture>
}