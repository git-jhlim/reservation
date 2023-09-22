package domain.lecture.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tb_lecture")
data class Lecture (
    @Id
    @Column(name = "lecture_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val no: Int = 0,
    @Column(length = 50)
    val lecturer: String,
    @Column(length = 30)
    val lectureRoom: String,
    @Column(length = 500)
    val description: String = "",
    val capacity: Int,
    @Column(nullable = false, name = "start_date")
    val startDateTime: LocalDateTime,
    @Column(name = "register_date")
    val registerDateTime: LocalDateTime = LocalDateTime.now(),
)