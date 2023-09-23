package com.exam.front.presentation.resevation

import com.exam.front.application.reservation.ReservationService
import com.exam.front.application.reservation.model.ReservationSearchModel
import com.exam.front.presentation.resevation.model.ReservationCreateRequest
import extention.queryParamOrThrow
import extention.queryParamToIntOrNull
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class ReservationHandler(private val reservationService: ReservationService){
    suspend fun reserveLecture(request: ServerRequest): ServerResponse {
        val createRequest = request.awaitBody<ReservationCreateRequest>()

        reservationService.reserveLecture(createRequest.toReservationCreateModel())
        return ServerResponse.noContent().buildAndAwait()
    }

    suspend fun getAllRigisteredLectures(request: ServerRequest): ServerResponse {
        val employeeId = request.queryParamOrThrow("employeeId")
        val page = request.queryParamToIntOrNull("page") ?: 1
        val size = request.queryParamToIntOrNull("size") ?: 20

        return ServerResponse.ok().bodyValueAndAwait(
            reservationService.getAllRigisteredLectures(
                ReservationSearchModel(page, size, employeeId)
            )
        )
    }

    suspend fun cancelLecture(request: ServerRequest): ServerResponse {
        return ServerResponse.noContent().buildAndAwait()
    }
}