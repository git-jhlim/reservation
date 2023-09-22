package com.exam.front.application.lecture.model

import domain.lecture.model.SearchParams

data class SearchModel(
    val page: Int,
    val size: Int,
) {
    fun toSearchParams() = SearchParams(page-1, size)
}