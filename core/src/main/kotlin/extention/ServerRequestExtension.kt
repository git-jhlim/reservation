package extention

import exception.InvalidArgumentException
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.queryParamOrNull

fun ServerRequest.queryParamToIntOrNull(name: String): Int? {
    return queryParamOrNull(name)?.toIntOrNull()
}

fun ServerRequest.queryParamOrThrow(name: String): String {
    return queryParamOrNull(name)
        ?: throw InvalidArgumentException(name)
}