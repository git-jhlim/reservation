package exception

class InvalidArgumentException(arg: String) : CommonException(APIErrorCode.INVALID_ARGUMENT, arrayOf(arg) )
class InvalidFieldValueException(args: Array<Any?>) : CommonException(APIErrorCode.EXCEEDED_LENGTH, args)