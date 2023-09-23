package domain.reservation.repository

import com.querydsl.core.types.Projections
import common.PageResponse
import domain.lecture.entity.QLecture
import domain.reservation.entity.QReservation
import domain.reservation.entity.Reservation
import domain.reservation.model.RegisteredLecture
import domain.reservation.model.ReservationSearchParams
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class ReservationRepositoryImpl: ReservationRepositoryCustom, QuerydslRepositorySupport(Reservation::class.java) {
    private val tbReservation = QReservation.reservation
    private val tbLecture = QLecture.lecture
    override fun findAllRegisteredLecturesBy(searchParams: ReservationSearchParams): PageResponse<RegisteredLecture> {
        val selectQuery = from(tbReservation)
            .where(tbReservation.employeeId.eq(searchParams.employeeId))

        val results = selectQuery
            .leftJoin(tbLecture)
            .on(tbLecture.no.eq(tbReservation.lectureNo))
            .select(
                Projections.constructor(
                    RegisteredLecture::class.java,
                    tbReservation.no,
                    tbLecture.lecturer,
                    tbLecture.lectureRoom,
                    tbLecture.description,
                    tbLecture.startDateTime,
                    tbReservation.registerDate,
                )
            )
            .orderBy(tbReservation.no.desc())
            .offset(searchParams.getOffset())
            .limit(searchParams.getLimit())
            .fetch()


        val count = selectQuery.fetchCount()

        return PageResponse(
            totalCount = count,
            page = searchParams.page,
            size = searchParams.size,
            contents = results,
        )
    }
}