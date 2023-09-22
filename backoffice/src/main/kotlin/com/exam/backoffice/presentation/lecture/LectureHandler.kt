package com.exam.backoffice.presentation.lecture

import com.exam.backoffice.presentation.lecture.model.LectureCreateRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class LectureHandler {
    suspend fun create(request: ServerRequest): ServerResponse {
        val createRequest = request.awaitBody<LectureCreateRequest>()
//        val keyword = request.queryParamOrThrow("keyword")
//        val sorting = request.queryParamOrNull("sorting")
//                ?.let {
//                    BlogSearchSort.getBy(it) ?: throw InvalidParameterException("sorting")
//                }
//        val page = request.queryParamToIntOrNull("page")
//        val size = request.queryParamToIntOrNull("size")
//
//        val param = BlogSearchRequest.of(keyword, sorting, page, size)
//                .also { it.valid() }
        return ServerResponse.noContent().buildAndAwait()
    }
}