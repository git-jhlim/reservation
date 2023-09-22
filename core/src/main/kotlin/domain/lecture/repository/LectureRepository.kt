package domain.lecture.repository

import domain.lecture.entity.Lecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

interface LectureRepository : JpaRepository<Lecture, Int>, LectureRepositoryCustom {
}