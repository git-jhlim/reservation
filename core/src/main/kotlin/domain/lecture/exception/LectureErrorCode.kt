package domain.lecture.exception

import common.PropertiesMessage
import exception.ErrorCode

enum class LectureErrorCode (
    override val code: String,
    override val messageKey: String,
): ErrorCode {
    NOT_FOUND("LCCD0001", "lectureErrorCode.notFound"),
    ;

    override fun getMessage(arg: String?): String {
        return PropertiesMessage.getMessage(messageKey, arrayOf(arg))
    }
}