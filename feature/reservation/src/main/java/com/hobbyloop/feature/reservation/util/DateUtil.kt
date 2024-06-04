package com.hobbyloop.feature.reservation.util

import java.text.SimpleDateFormat
import java.util.Locale

object DateUtil {

    fun formatDateTimeRange(dateTimeRange: String): String {
        val inputFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormatter = SimpleDateFormat("yyyy.MM.dd", Locale.getDefault())

        val dateTimeParts = dateTimeRange.split(" - ")
        val startLegacyDate = inputFormatter.parse(dateTimeParts[0])
        val endLegacyDate = inputFormatter.parse("${dateTimeParts[0].substring(0, 10)} ${dateTimeParts[1]}")

        val formattedStartDateTime = startLegacyDate?.let { outputFormatter.format(it) }
        val formattedEndDateTime = endLegacyDate?.let { outputFormatter.format(it) }

        return "$formattedStartDateTime - $formattedEndDateTime"
    }
}
