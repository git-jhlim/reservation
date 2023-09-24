package domain.reservation.exception

import common.PropertiesMessage
import exception.ErrorCode

enum class ReservationErrorCode(
	override val code: String,
	override val messageKey: String,
): ErrorCode {
	NOT_FOUND("RVCD0001", "reservationErrorCode.notFound"),
	ALREADY_EXIST("RVCD0002", "reservationErrorCode.alreadyExist"),
	EXCEEDED_RESERVATION("RVCD0003", "reservationErrorCode.exceededReservation"),
	;
	
	override fun getMessage(args: Array<Any?>): String {
		return PropertiesMessage.getMessage(messageKey, args)
	}
}