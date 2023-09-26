package com.exam.backoffice.presentation.lecture

import com.exam.backoffice.application.lecture.LectureService
import com.exam.backoffice.application.lecture.model.LectureSearchModel
import com.exam.backoffice.presentation.lecture.model.LectureCreateRequest
import exception.InvalidArgumentException
import extention.awaitBodyOrThrow
import extention.queryParamToIntOrNull
import io.netty.handler.codec.DecoderException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class LectureHandler(private val lectureService: LectureService) {
    suspend fun create(request: ServerRequest): ServerResponse {
        val createRequest = request.awaitBodyOrThrow<LectureCreateRequest>()
            .also { it.validate() }

        lectureService.create(createRequest.toLectureCreateModel())
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun getLectures(request: ServerRequest): ServerResponse {
        val page = request.queryParamToIntOrNull("page") ?: 1
        val size = request.queryParamToIntOrNull("size") ?: 20
        return ServerResponse.ok().bodyValueAndAwait(
            lectureService.getLectures(LectureSearchModel(page, size))
        )
    }
    
    suspend fun getAudiences(request: ServerRequest): ServerResponse {
        val lectureNo = request.pathVariable("lectureNo").toIntOrNull()
            ?: throw InvalidArgumentException("lectureNo")
        
        return ServerResponse.ok().bodyValueAndAwait(
            lectureService.getAudiences(lectureNo)
        )
    }
}