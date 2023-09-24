package exception

open class CommonException(
	val errorCode: ErrorCode,
	val args: Array<Any?> = emptyArray(),
	override val message: String = errorCode.getMessage(args),
): RuntimeException(message)