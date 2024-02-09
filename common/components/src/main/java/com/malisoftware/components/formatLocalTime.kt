package com.malisoftware.components

import androidx.compose.runtime.Composable


fun formatLocalTime(time: String): String {
    val timeParts = time.split(":")
    return "${timeParts[0]}:${timeParts[1]}"
}