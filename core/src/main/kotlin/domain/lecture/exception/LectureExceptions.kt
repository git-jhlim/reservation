package domain.lecture.exception

import exception.CommonException

class LectureNotFoundException: CommonException(LectureErrorCode.NOT_FOUND)