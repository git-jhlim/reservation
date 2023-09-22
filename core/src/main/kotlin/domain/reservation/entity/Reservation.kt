package domain.reservation.entity

import jakarta.persistence.*
import org.hibernate.annotations.Where
import java.time.LocalDateTime

@Entity
@Table(name = "tb_lecture_reservation")
@Where(clause = "is_canceled = false")
data class Reservation (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_no")
    val no: Int = 0,
    val lectureNo: Int,
    val employeeId: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    val updateDate: LocalDateTime? = null,
    val isCanceled: Boolean = false,
)