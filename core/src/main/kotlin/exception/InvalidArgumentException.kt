package exception

class InvalidArgumentException(arg: String) : CommonException(APIErrorCode.INVALID_ARGUMENT, arg)