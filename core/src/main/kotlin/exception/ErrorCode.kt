package exception

interface ErrorCode {
	val code: String
	val messageKey: String
	fun getMessage(args: Array<Any?>): String
}