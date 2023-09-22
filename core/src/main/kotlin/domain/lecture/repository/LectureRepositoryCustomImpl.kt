package domain.lecture.repository

import common.PageResponse
import domain.lecture.entity.Lecture
import domain.lecture.entity.QLecture
import domain.lecture.model.SearchParams
import domain.lecture.repository.LectureRepositoryCustom
import extention.atEndOfDay
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
class LectureRepositoryCustomImpl: LectureRepositoryCustom, QuerydslRepositorySupport(Lecture::class.java) {
    private val tbLecture = QLecture.lecture

    override fun getAvailableLectures(searchParams: SearchParams): PageResponse<Lecture> {
        val startDate = LocalDate.now().atStartOfDay()
        val endDate = LocalDate.now().plusDays(7).atEndOfDay()

        val selectQuery = from(tbLecture)
            .where(
                tbLecture.startDateTime.between(startDate, endDate)
            )
        val count = selectQuery
            .fetchCount()

        val lectures =  from(tbLecture)
            .where(
                tbLecture.startDateTime.between(startDate, endDate)
            ).orderBy(tbLecture.startDateTime.desc())
            .offset(searchParams.getOffset())
            .limit(searchParams.getLimit())
            .fetch()

        return PageResponse(
            totalCount = count,
            page = searchParams.page,
            size = searchParams.size,
            contents = lectures,
        )
    }
}