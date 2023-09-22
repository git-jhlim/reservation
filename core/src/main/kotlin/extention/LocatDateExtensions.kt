package extention

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun LocalDate.atEndOfDay() = LocalDateTime.of(this, LocalTime.MAX)