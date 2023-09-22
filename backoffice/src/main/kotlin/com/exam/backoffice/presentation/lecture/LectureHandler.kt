package com.exam.backoffice.presentation.lecture

import com.exam.backoffice.application.lecture.LectureService
import com.exam.backoffice.presentation.lecture.model.LectureCreateRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.lang.Exception

@Component
class LectureHandler(private val lectureService: LectureService) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val createRequest = request.awaitBody<LectureCreateRequest>()

        lectureService.create(createRequest.toLectureCreateModel())
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun getAll(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(
            lectureService.getAll()
        )
    }
}