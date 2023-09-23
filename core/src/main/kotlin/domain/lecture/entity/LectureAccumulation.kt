package domain.lecture.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "tb_lecture_accumulation")
data class LectureAccumulation (
	@Id
	val lectureNo: Int,
	val popularity: Int,
)