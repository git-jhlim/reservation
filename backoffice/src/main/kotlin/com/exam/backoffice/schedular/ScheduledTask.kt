package com.exam.backoffice.schedular

import domain.lecture.entity.LectureAccumulation
import domain.lecture.repository.LectureAccumulationRepository
import domain.reservation.repository.ReservationRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ScheduledTask(
	private val reservationRepository: ReservationRepository,
	private val lectureAccumulationRepository: LectureAccumulationRepository,
) {
	@Scheduled(cron = "0 */10 * * * *")
	@Transactional
	fun updateLectureAccumulation() {
		lectureAccumulationRepository.deleteAll()
		val accumulations = reservationRepository.getCountGroupByLectureNo()
			.map {(lectureNo, cnt) ->
				LectureAccumulation(lectureNo, cnt.toInt())
			}
		
		lectureAccumulationRepository.saveAll(accumulations)
	}
}