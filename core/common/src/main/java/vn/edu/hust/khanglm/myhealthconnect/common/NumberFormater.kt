package vn.edu.hust.khanglm.myhealthconnect.common

import java.util.Locale

fun Double.formatToString(): String {
    return String.format(Locale.getDefault(), "%.2f", this)
}