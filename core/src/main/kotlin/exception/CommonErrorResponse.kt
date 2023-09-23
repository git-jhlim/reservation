package exception

import java.time.LocalDateTime

data class CommonErrorResponse (
	val errorCode: String,
	val time: LocalDateTime = LocalDateTime.now(),
	val message: String = "",
)