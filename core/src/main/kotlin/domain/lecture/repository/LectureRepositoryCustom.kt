package domain.lecture.repository

import common.PageResponse
import domain.lecture.entity.Lecture
import domain.lecture.model.LectureSearchParams

interface LectureRepositoryCustom {
    fun getAvailableLectures(searchParams: LectureSearchParams): PageResponse<Lecture>
    fun existLecture(lectureNo: Int): Boolean
}