package domain.lecture.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Lecture (
    @Id
    @Column(name = "lecture_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val no: Int = 0,
    @Column(length = 50)
    val lecturer: String,
    @Column(length = 30)
    val lectureRoom: String,
    val capacity: Int,
    @Column(nullable = false)
    val startDateTime: LocalDateTime,
    @Column(length = 500)
    val summary: String = "",
)