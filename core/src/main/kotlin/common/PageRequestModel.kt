package common

import org.springframework.data.domain.PageRequest

open class PageRequestModel(
    _page: Int,
    _size: Int,
) {
    private val page = _page
    private val size = _size

    private val pageable = PageRequest.of(page-1, size)

    fun getLimit() = pageable.pageSize.toLong()
    fun getOffset() = pageable.offset
}