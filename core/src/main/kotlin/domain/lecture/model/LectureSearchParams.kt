package domain.lecture.model

import common.PageRequestModel


data class LectureSearchParams (
    val page: Int,
    val size: Int,
): PageRequestModel(page, size)