package com.monefy.mobile.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class DateUtils {
    companion object {
        fun getCurrentDateFormatted(): String {
            val currentDate = LocalDate.now()
            val formatter = DateTimeFormatter.ofPattern("dd MMM", Locale.ENGLISH)
            return currentDate.format(formatter)
        }
    }
} 