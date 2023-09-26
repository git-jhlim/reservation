package com.exam.front.presetation.reservation

import com.exam.front.application.reservation.ReservationService
import com.exam.front.presentation.resevation.ReservationHandler
import com.exam.front.presentation.resevation.model.ReservationCreateRequest
import common.PageResponse
import exception.APIErrorCode
import exception.HasNoRequestBodyException
import exception.InvalidArgumentException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mock.web.reactive.function.server.MockServerRequest
import reactor.core.publisher.Mono

@ExtendWith(MockKExtension::class)
class ReservationHandlerTest {
	@MockK
	lateinit var reservationService: ReservationService
	lateinit var reservationHandler: ReservationHandler
	
	@BeforeEach
	fun setup() {
		reservationHandler = ReservationHandler(reservationService)
	}
	
	@Test
	fun `강의 신청 시 빈 사번이 5자리가 아닌 경우 exception` () {
		val requestBody = ReservationCreateRequest("1111111", 1)
		val request = MockServerRequest.builder().body(Mono.just(requestBody))
		
		coEvery { reservationService.reserveLecture(any()) } just runs
		
		val exception = assertThrows<InvalidArgumentException> {
			runBlocking {
				reservationHandler.reserveLecture(request)
			}
		}
		
		assert(exception.errorCode == APIErrorCode.INVALID_ARGUMENT)
	}
	
	@Test
	fun `강의 신청 목록 조회 시, 사번미 입력 시, exception` () {
		val request = MockServerRequest.builder().build()
		
		val exception = assertThrows<InvalidArgumentException> {
			runBlocking {
				reservationHandler.getAllRigisteredLectures(request)
			}
		}
		
		assert(exception.errorCode == APIErrorCode.INVALID_ARGUMENT)
		assert(exception.args.first() == "employeeId")
	}
	
	@Test
	fun `강의 신청 목록 조회` () {
		val request = MockServerRequest.builder().queryParam("employeeId","12345").build()
		
		coEvery { reservationService.getAllRigisteredLectures(any()) } returns  PageResponse(0,1,10, emptyList())
		
		runBlocking {
			reservationHandler.getAllRigisteredLectures(request)
		}
		
		coVerify { reservationService.getAllRigisteredLectures(any()) }
	}
	
	//cancelLecture
	@Test
	fun `강의 취소 시, 예약 번호가 숫자외의 다른 문자인 경우 exception` () {
		val request = MockServerRequest.builder()
			.pathVariable("reservationNo","no")
			.queryParam("employeeId","12345")
			.build()
		
		val exception = assertThrows<InvalidArgumentException> {
			runBlocking {
				reservationHandler.cancelLecture(request)
			}
		}
		
		assert(exception.errorCode == APIErrorCode.INVALID_ARGUMENT)
		assert(exception.args.first() == "reservationNo")
	}
	
	@Test
	fun `강의 취소 시, 사번 미 입력한 경우 exception` () {
		val request = MockServerRequest.builder()
			.pathVariable("reservationNo","1")
			.build()
		
		val exception = assertThrows<InvalidArgumentException> {
			runBlocking {
				reservationHandler.cancelLecture(request)
			}
		}
		
		assert(exception.errorCode == APIErrorCode.INVALID_ARGUMENT)
		assert(exception.args.first() == "employeeId")
	}
}