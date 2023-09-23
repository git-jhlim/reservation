package domain.reservation.exception

import common.PropertiesMessage
import exception.ErrorCode

enum class ReservationErrorCode(
	override val code: String,
	override val messageKey: String,
): ErrorCode {
	NOT_FOUND("RVCD0001", "reservationErrorCode.notFound"),
	ALREADY_EXIST("RVCD0002", "reservationErrorCode.alreadyExist"),
	;
	
	override fun getMessage(arg: String?): String {
		return PropertiesMessage.getMessage(messageKey, arrayOf(arg))
	}
}