package exception

open class CommonException(
	val errorCode: ErrorCode,
	val args: Array<Any?> = emptyArray(),
	override val message: String = errorCode.getMessage(args),
): RuntimeException(message)

class InvalidArgumentException(arg: String) : CommonException(APIErrorCode.INVALID_ARGUMENT, arrayOf(arg) )
class InvalidFieldValueException(args: Array<Any?>) : CommonException(APIErrorCode.EXCEEDED_LENGTH, args)
class HasNoRequestBodyException() : CommonException(APIErrorCode.HAS_NO_REQUEST_BODY)
class InvalidRequestBodyException() : CommonException(APIErrorCode.INVALID_REQUEST_BODY)