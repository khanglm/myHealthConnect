package vn.edu.hust.khanglm.myhealthconnect.common

import android.icu.util.Calendar
import android.icu.util.TimeZone
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZoneId
import java.util.Date
import java.util.Locale

fun String.convertToTimeMillis(): Long {
    return try {
        val sfd = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.getDefault())
        val time = sfd.parse(this) ?: Date()
        time.time
    } catch (e: Exception) {
        0L
    }
}

fun Long.convertToDDMMMYYYYFormat(): String {
    return SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(
        Date().apply { time = this@convertToDDMMMYYYYFormat }
    )
}

fun calculateTimeToday(): Pair<Long, Long> {
    val startTime = Calendar.getInstance()
        .apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECONDS_IN_DAY, 0)
        }.timeInMillis
    val endTime = startTime + 24 * 3600 * 1000
    return Pair(startTime, endTime)
}

fun Long.getRangeTimeInDay(): Pair<Long, Long> {
    val currentTime = Calendar.getInstance(TimeZone.getDefault()).apply { timeInMillis = this@getRangeTimeInDay }
    val endTime = currentTime
        .apply {
            set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) + 1)
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 59)
            set(Calendar.MILLISECONDS_IN_DAY, 0)
        }.timeInMillis
    val startTime = (endTime - 24 * 3600 * 1000).coerceAtLeast(0)
    return Pair(startTime, endTime)
}

fun calculateTimeSyncData(): Pair<Long, Long> {
    val endTime = Calendar.getInstance()
        .apply {
            set(Calendar.DAY_OF_YEAR, get(Calendar.DAY_OF_YEAR) + 1)
            set(Calendar.HOUR_OF_DAY, 23)
            set(Calendar.MINUTE, 59)
            set(Calendar.SECOND, 29)
            set(Calendar.MILLISECONDS_IN_DAY, 0)
        }.timeInMillis
    val startTime = (endTime - 7 * 24 * 3600 * 1000).coerceAtLeast(0L)
    return Pair(startTime, endTime)
}

fun Long.toInstant(): Instant {
    return Instant.ofEpochMilli(this).apply {
        atZone(ZoneId.systemDefault())
    }
}