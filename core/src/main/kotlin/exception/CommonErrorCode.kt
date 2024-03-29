package exception

import common.PropertiesMessage

enum class CommonErrorCode(
	override val code: String,
	override val messageKey: String,
): ErrorCode {
	INTERNAL_ERROR("CECD0001", "commonErrorCode.internalServerError"),
	BAD_REQUEST("CECD0002", "commonErrorCode.badRequest"),
	;
	
	override fun getMessage(args: Array<Any?>): String {
		return PropertiesMessage.getMessage(messageKey, args)
	}
}