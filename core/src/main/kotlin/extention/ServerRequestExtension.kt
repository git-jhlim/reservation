package extention

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.queryParamOrNull
import java.lang.RuntimeException

fun ServerRequest.queryParamToIntOrNull(name: String): Int? {
    return queryParamOrNull(name)?.toIntOrNull()
}

fun ServerRequest.queryParamOrThrow(name: String): String {
    return queryParamOrNull(name)
        ?: throw RuntimeException("$name 을 확인 해 주세요.")
}