package dtu.android.moroapp.models.event

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

typealias TimeDate = Long

fun TimeDate.toLocalDateTime() = Instant
        .ofEpochSecond(this)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()

fun TimeDate.format(pattern: String) = this.toLocalDateTime()
        .format(DateTimeFormatter.ofPattern(pattern))



