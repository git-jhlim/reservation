package exception

import common.PropertiesMessage

enum class APIErrorCode(
	override val code: String,
	override val messageKey: String,
): ErrorCode {
	INVALID_ARGUMENT("PE0001", "presentationErrorCode.invalidArgument"),
	EXCEEDED_LENGTH("PE0002", "presentationErrorCode.exceededLength"),
	;
	
	override fun getMessage(args: Array<Any?>): String {
		return PropertiesMessage.getMessage(messageKey, args)
	}
}