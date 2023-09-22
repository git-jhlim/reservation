package com.exam.backoffice.presentation.lecture

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class LectureRouter(private val lectureHandler: LectureHandler) {
    @Bean
    fun routeLecture(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/lectures").nest {
                POST("", lectureHandler::create)
                GET("", lectureHandler::getAll)
            }
        }
    }
}