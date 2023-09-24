package domain.lecture.repository

import domain.lecture.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository : JpaRepository<Lecture, Int>, LectureRepositoryCustom {
	fun getByNo(lectureNo: Int): Lecture?
}