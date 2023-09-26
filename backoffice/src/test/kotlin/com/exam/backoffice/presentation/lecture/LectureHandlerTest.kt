package com.exam.backoffice.presentation.lecture

import com.exam.backoffice.application.lecture.LectureService
import com.exam.backoffice.presentation.lecture.model.LectureCreateRequest
import exception.APIErrorCode
import exception.InvalidArgumentException
import exception.InvalidFieldValueException
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.reactive.function.server.MockServerRequest
import reactor.core.publisher.Mono
import java.time.LocalDate

@ExtendWith(MockKExtension::class)
class LectureHandlerTest {
	@MockK
	lateinit var lectureService: LectureService
	lateinit var lectureHandler: LectureHandler
	
	@BeforeEach
	fun setup() {
		lectureHandler = LectureHandler(lectureService)
	}
	
	@Test
	fun `수강생 조회 시 강연번호 숫자 외 입력시 exception 발생` () {
		val request = MockServerRequest.builder()
			.pathVariable("lectureNo","lecture")
			.build()
		
		val exception = assertThrows<InvalidArgumentException> {
			runBlocking {
				lectureHandler.getAudiences(request)
			}
		}
		
		assert(exception.errorCode == APIErrorCode.INVALID_ARGUMENT)
		assert(exception.args.first() == "lectureNo")
	}
	
	@Test
	fun `강의 등록시, 강연자명은 최대 50자`() {
		val requestBody = LectureCreateRequest(
			"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십1"
			,"201호",
			100,
			LectureCreateRequest.LectureDateTime(LocalDate.now(), 12, 10),
			"",
		)
		
		val request = MockServerRequest.builder().body(Mono.just(requestBody))
		
		coEvery { lectureService.create(any()) } just runs
		
		val exception = assertThrows<InvalidFieldValueException> {
			runBlocking { lectureHandler.create(request) }
		}
		
		assert(exception.errorCode == APIErrorCode.EXCEEDED_LENGTH)
		assert(exception.args.first() == "lecturer")
	}
	
	@Test
	fun `강의 등록시, 강의실은 최대 30자`() {
		val requestBody = LectureCreateRequest(
			"홍길동"
			,"일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십1",
			100,
			LectureCreateRequest.LectureDateTime(LocalDate.now(), 12, 10),
			"",
		)
		
		val request = MockServerRequest.builder().body(Mono.just(requestBody))
		
		coEvery { lectureService.create(any()) } just runs
		
		val exception = assertThrows<InvalidFieldValueException> {
			runBlocking { lectureHandler.create(request) }
		}
		
		assert(exception.errorCode == APIErrorCode.EXCEEDED_LENGTH)
		assert(exception.args.first() == "lectureRoom")
	}
}