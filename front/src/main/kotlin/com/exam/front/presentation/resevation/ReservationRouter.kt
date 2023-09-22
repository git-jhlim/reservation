package com.exam.front.presentation.resevation

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class ReservationRouter(private val reservationHandler: ReservationHandler){
    @Bean
    fun routeFrontReservation(): RouterFunction<ServerResponse> {
        return coRouter {
            (accept(MediaType.APPLICATION_JSON) and "/reservations/").nest {
                POST("/lectures", reservationHandler::reserveLecture)
            }
        }
    }
}