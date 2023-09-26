package extention

import exception.HasNoRequestBodyException
import exception.InvalidArgumentException
import exception.InvalidRequestBodyException
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.awaitBody
import org.springframework.web.reactive.function.server.queryParamOrNull
import org.springframework.web.server.ServerWebInputException

fun ServerRequest.queryParamToIntOrNull(name: String): Int? {
    return queryParamOrNull(name)?.toIntOrNull()
}

fun ServerRequest.queryParamOrThrow(name: String): String {
    return queryParamOrNull(name)
        ?: throw InvalidArgumentException(name)
}

suspend inline fun <reified T : Any> ServerRequest.awaitBodyOrThrow(): T{
    try {
        return awaitBody<T>()
    } catch (ex: Exception) {
        when(ex) {
            is NoSuchElementException -> throw HasNoRequestBodyException()
            is ServerWebInputException -> throw InvalidRequestBodyException()
            else -> throw ex
        }
    }
}