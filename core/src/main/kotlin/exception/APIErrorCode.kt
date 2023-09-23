package exception

import common.PropertiesMessage

enum class APIErrorCode(
	override val code: String,
	override val messageKey: String,
): ErrorCode {
	INVALID_ARGUMENT("PE0001", "presentationErrorCode.invalidArgument"),
	;
	
	override fun getMessage(arg: String?): String {
		return PropertiesMessage.getMessage(messageKey, arrayOf(arg))
	}
}