package domain.lecture.repository

import domain.lecture.entity.LectureAccumulation
import org.springframework.data.jpa.repository.JpaRepository

interface LectureAccumulationRepository: JpaRepository<LectureAccumulation, Int> {
}