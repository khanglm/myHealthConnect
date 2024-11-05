package vn.edu.hust.khanglm.myhealthconnect.common

import java.text.SimpleDateFormat
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