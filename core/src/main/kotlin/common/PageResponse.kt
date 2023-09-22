package common

data class PageResponse<T> (
    val totalCount: Long,
    val page: Int,
    val size: Int,
    val contents: List<T>,
) {
    companion object {
        fun <T, V> convert(page: PageResponse<T>, transform: (T) -> V): PageResponse<V> {
            return PageResponse(
                totalCount = page.totalCount,
                page = page.page,
                size = page.size,
                contents = page.contents.map { transform(it) },
            )
        }
    }
}