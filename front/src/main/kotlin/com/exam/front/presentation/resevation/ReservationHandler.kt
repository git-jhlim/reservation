package com.exam.front.presentation.resevation

import com.exam.front.application.reservation.ReservationService
import com.exam.front.presentation.resevation.model.ReservationCreateRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class ReservationHandler(private val reservationService: ReservationService){
    suspend fun reserveLecture(request: ServerRequest): ServerResponse {
        val createRequest = request.awaitBody<ReservationCreateRequest>()

        reservationService.reserveLecture(createRequest.toReservationCreateModel())
        return ServerResponse.noContent().buildAndAwait()
    }
}