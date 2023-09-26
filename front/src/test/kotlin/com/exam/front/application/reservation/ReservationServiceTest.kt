package com.exam.front.application.reservation

import ch.qos.logback.core.testUtil.RandomUtil
import com.exam.front.application.reservation.model.ReservationCreateModel
import com.exam.front.application.reservation.model.ReservationSearchModel
import com.exam.front.infrastructure.redis.ReactiveRedisAdaptor
import common.PageResponse
import domain.lecture.entity.Lecture
import domain.lecture.exception.LectureNotFoundException
import domain.lecture.repository.LectureRepository
import domain.reservation.entity.Reservation
import domain.reservation.exception.AlreadyReservedException
import domain.reservation.exception.ExceededReservationException
import domain.reservation.exception.ReservationNotFoundException
import domain.reservation.model.RegisteredLecture
import domain.reservation.repository.ReservationRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDateTime
import java.util.Random

@ExtendWith(MockKExtension::class)
class ReservationServiceTest {
	@MockK
	lateinit var lectureRepository: LectureRepository
	@MockK
	lateinit var reservationRepository: ReservationRepository
	@MockK
	lateinit var redisAdaptor: ReactiveRedisAdaptor
	
	lateinit var reservationService: ReservationService
	
	@BeforeEach
	fun setup() {
		reservationService = ReservationService(reservationRepository, lectureRepository, redisAdaptor)
	}
	
	
	@Test
	fun `강연 신청 시, 없는 강의 번호 의 경우 exception`() {
		val lectureNo = 1
		val createModel = ReservationCreateModel("12345", lectureNo)
		
		every { lectureRepository.getByNo(lectureNo) } returns null
		
		assertThrows<LectureNotFoundException> {
			runBlocking { reservationService.reserveLecture(createModel) }
		}
	}
	
	@Test
	fun `강연 신청 시, 인원 초과인 경우 exception`() {
		val lectureNo = 1
		val createModel = ReservationCreateModel("12345", lectureNo)
		val lecture = Lecture(
			no = 1,
			lecturer = "김철수",
			lectureRoom = "201호",
			capacity = 100,
			startDateTime = LocalDateTime.now(),
		)
		
		every { lectureRepository.getByNo(lectureNo) } returns lecture
		every { reservationRepository.countByLectureNo(lectureNo) } returns lecture.capacity
		coEvery { redisAdaptor.getIncrementValue(any(), any()) } returns 101
		
		assertThrows<ExceededReservationException> {
			runBlocking { reservationService.reserveLecture(createModel) }
		}
	}
	
	@Test
	fun `강연 신청 시, 이미 신청한 경우 exception`() {
		val lectureNo = 1
		val createModel = ReservationCreateModel("12345", lectureNo)
		val lecture = Lecture(
			no = 1,
			lecturer = "김철수",
			lectureRoom = "201호",
			capacity = 100,
			startDateTime = LocalDateTime.now(),
		)
		val reservationCnt = 10
		
		every { lectureRepository.getByNo(lectureNo) } returns lecture
		every { reservationRepository.countByLectureNo(lectureNo) } returns reservationCnt
		coEvery { redisAdaptor.getIncrementValue(any(), any()) } returns (reservationCnt + 1L)
		every { reservationRepository.findByLectureNoAndEmployeeId(createModel.lectureNo, createModel.employeeId) } returns mockk<Reservation>()
		
		assertThrows<AlreadyReservedException> {
			runBlocking { reservationService.reserveLecture(createModel) }
		}
	}
	
	@Test
	fun `강연 신청`() {
		val lectureNo = 1
		val createModel = ReservationCreateModel("12345", lectureNo)
		val lecture = Lecture(
			no = 1,
			lecturer = "김철수",
			lectureRoom = "201호",
			capacity = 100,
			startDateTime = LocalDateTime.now(),
		)
		val reservationCnt = 10
		
		every { lectureRepository.getByNo(lectureNo) } returns lecture
		every { reservationRepository.countByLectureNo(lectureNo) } returns reservationCnt
		coEvery { redisAdaptor.getIncrementValue(any(), any()) } returns (reservationCnt + 1L)
		every { reservationRepository.findByLectureNoAndEmployeeId(createModel.lectureNo, createModel.employeeId) } returns null
		coEvery { reservationRepository.save(any()) } returns mockk<Reservation>()
		
		runBlocking { reservationService.reserveLecture(createModel) }
		
		coVerify { reservationRepository.save(any()) }
	}
	
	@Test
	fun `신청한 강연 목록 조회 `() {
		val searchModel = ReservationSearchModel(1, 10, "12345")
		val registeredLecture = RegisteredLecture(
			1, "김김김", "101호", "", LocalDateTime.now(), LocalDateTime.now()
		)
		val lectures = PageResponse(1, 1, 10, listOf(registeredLecture))
		
		every { reservationRepository.findAllRegisteredLecturesBy(any()) } returns lectures
		
		val response = runBlocking { reservationService.getAllRigisteredLectures(searchModel) }
		
		coVerify(exactly = 1) { reservationRepository.findAllRegisteredLecturesBy(any()) }
		assert(response.totalCount == lectures.totalCount)
	}
	
	@Test
	fun `신청취소 시, 신청내역이 없는 경우 exception`() {
		val employeeId = "12345"
		val reservationNo = RandomUtil.getPositiveInt()
		
		every { reservationRepository.findByNoAndEmployeeId(reservationNo, employeeId) } returns null
		
		assertThrows<ReservationNotFoundException>{ runBlocking { reservationService.cancelReservation(employeeId, reservationNo) } }
	}
	
	@Test
	fun `신청취소`() {
		val employeeId = "12345"
		val reservationNo = RandomUtil.getPositiveInt()
		val reservation = mockk<Reservation>()
		
		every { reservationRepository.findByNoAndEmployeeId(reservationNo, employeeId) } returns reservation
		coEvery { reservation.cancel() } just runs
		
		runBlocking { reservationService.cancelReservation(employeeId, reservationNo) }
		
		coVerify(exactly = 1) { reservation.cancel() }
	}
}