package com.exam.front.application.lecture.model

import domain.lecture.model.LectureSearchParams

data class LectureSearchModel(
    val page: Int,
    val size: Int,
) {
    fun toLectureSearchParams() = LectureSearchParams(page, size)
}