package domain.lecture.model

import org.springframework.data.domain.PageRequest

data class SearchParams (
    val page: Int,
    val size: Int,
) {
    private val pageable = PageRequest.of(page, size)

    fun getLimit() = pageable.pageSize.toLong()
    fun getOffset() = pageable.offset

}