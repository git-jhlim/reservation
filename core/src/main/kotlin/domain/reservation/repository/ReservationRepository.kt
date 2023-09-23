package domain.reservation.repository

import domain.reservation.entity.Reservation
import org.springframework.data.jpa.repository.JpaRepository

interface ReservationRepository: JpaRepository<Reservation, Int>, ReservationRepositoryCustom {
    fun findByLectureNoAndEmployeeId(lectureNo: Int, employeeId: String): Reservation?
}