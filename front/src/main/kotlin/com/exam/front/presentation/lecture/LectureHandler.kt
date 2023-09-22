package com.exam.front.presentation.lecture

import com.exam.front.application.lecture.LectureService
import com.exam.front.application.lecture.model.SearchModel
import extention.queryParamToIntOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class LectureHandler(private val lectureService: LectureService) {
    suspend fun search(request: ServerRequest): ServerResponse {
        val page = request.queryParamToIntOrNull("page") ?: 1
        val size = request.queryParamToIntOrNull("size") ?: 20

        return ServerResponse.ok().bodyValueAndAwait(
            lectureService.search(SearchModel(page, size))
        )
    }
}