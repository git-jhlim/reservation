package com.exam.front.application.lecture

import com.exam.front.application.lecture.model.LectureInfo
import com.exam.front.application.lecture.model.LectureSearchModel
import common.PageResponse
import domain.lecture.entity.Lecture
import domain.lecture.repository.LectureRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime

@ExtendWith(MockKExtension::class)
class LectureServiceTest {
	@MockK
	lateinit var lectureRepository: LectureRepository
	
	lateinit var lectureService: LectureService
	
	@BeforeEach
	fun setup() {
		lectureService = LectureService(lectureRepository)
	}
	
	@Test
	fun `강연 목록 조회`() {
		val searchModel = LectureSearchModel(1, 10)
		
		every { lectureRepository.getAvailableLectures(any()) } returns PageResponse(0,1,10, emptyList())
		
		val response = runBlocking {
			lectureService.search(searchModel)
		}
		
		coVerify { lectureRepository.getAvailableLectures(any()) }
		assert(response.totalCount == 0L)
	}
	
	@Test
	fun `인기 강연 목록 조회`() {
		val lectures = listOf(
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

		every { lectureRepository.getAllOrderByReservationCnt() } returns lectures

		val response = runBlocking {
			lectureService.getByPopularity()
		}

		coVerify { lectureRepository.getAllOrderByReservationCnt() }
		assert(response.size == lectures.size)
	}
}