package com.exam.backoffice.presentation.lecture

import com.exam.backoffice.application.lecture.LectureService
import com.exam.backoffice.presentation.lecture.model.LectureCreateRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.lang.RuntimeException

@Component
class LectureHandler(private val lectureService: LectureService) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val createRequest = request.awaitBody<LectureCreateRequest>()

        lectureService.create(createRequest.toLectureCreateModel())
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun getLectures(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(
            lectureService.getLectures()
        )
    }
    
    suspend fun getAudiences(request: ServerRequest): ServerResponse {
        val lectureNo = request.pathVariable("lectureNo").toIntOrNull()
            ?: throw RuntimeException("lectureNo 를 확인 해 주세요.")
        
        return ServerResponse.ok().bodyValueAndAwait(
            lectureService.getAudiences(lectureNo)
        )
    }
}