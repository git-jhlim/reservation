package com.exam.backoffice.application.lecture

import com.exam.backoffice.application.lecture.model.LectureCreateModel
import com.exam.backoffice.application.lecture.model.LectureSearchModel
import com.exam.backoffice.presentation.lecture.LectureHandler
import common.PageResponse
import domain.lecture.entity.Lecture
import domain.lecture.exception.LectureErrorCode
import domain.lecture.exception.LectureNotFoundException
import domain.lecture.repository.LectureRepository
import domain.reservation.entity.Reservation
import domain.reservation.repository.ReservationRepository
import exception.InvalidFieldValueException
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import jakarta.persistence.Column
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class LectureServiceTest {
	@MockK
	lateinit var lectureRepository: LectureRepository
	@MockK
	lateinit var reservationRepository: ReservationRepository
	
	lateinit var lectureService: LectureService
	
	@BeforeEach
	fun setup() {
		lectureService = LectureService(lectureRepository, reservationRepository)
	}
	
	@Test
	fun `강연 등록`() {
		val createModel = LectureCreateModel(
			"홍길동",
			"102호",
			50,
			LocalDateTime.now(),
			"효과적인 마켓팅"
		)
		
		every { lectureRepository.save(any()) } returns createModel.toLecture()
		
		runBlocking {
			lectureService.create(createModel)
		}
		
		coVerify { lectureRepository.save(any()) }
	}
	
	@Test
	fun `강연 목록 조회`() {
		val searchModel = LectureSearchModel(1, 10)
		val contents = listOf(
			Lecture (
				no = 1,
				lecturer = "김얼수",
				lectureRoom = "101실",
				capacity = 100,
				startDateTime = LocalDateTime.now(),
			),
			Lecture (
				no = 2,
				lecturer = "김영희",
				lectureRoom = "101실",
				capacity = 100,
				startDateTime = LocalDateTime.now(),
			)
		)
		val results = PageResponse (2, 1,10, contents)
		
		every { lectureRepository.getAllLectures(any()) } returns results
		
		val response = runBlocking {
			lectureService.getLectures(searchModel)
		}
		
		assert(response.totalCount == results.totalCount)
		assert(response.size == results.size)
		assert(response.page == results.page)
		assert(response.contents.size == results.contents.size)
	}
	
	@Test
	fun `강연 별 신청 사번 조회 시, 없는 강의번호 조회 시 exception`() {
		val lectureNo = 1
		
		every { lectureRepository.existLecture(lectureNo) } returns false
		
		val exception = assertThrows<LectureNotFoundException> {
			runBlocking { lectureService.getAudiences(lectureNo) }
		}
		
		assert(exception.errorCode == LectureErrorCode.NOT_FOUND)
	}
	
	@Test
	fun `강연 별 신청 사번 조회`() {
		val lectureNo = 1
		val results = listOf(
			Reservation(1, 1, "12345"),
			Reservation(2, 1, "54321"),
		)
		
		every { lectureRepository.existLecture(lectureNo) } returns true
		every { reservationRepository.findByLectureNo(lectureNo) } returns results
		
		val response = runBlocking { lectureService.getAudiences(lectureNo) }
		
		assert(response.size == results.size)
		assert(response.containsAll(results.map { it.employeeId }))
	}
}