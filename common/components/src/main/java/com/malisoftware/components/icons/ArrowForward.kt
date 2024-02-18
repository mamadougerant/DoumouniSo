package com.malisoftware.components.icons

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.ButtonSizes

@Composable
fun ArrowForward (
    onClick: () -> Unit = {}
){
    val hapticFeedback = LocalHapticFeedback.current
    IconButton(
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        modifier = Modifier
            .size(ButtonSizes.Dp24),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = "",
            modifier = Modifier
                .size(20.dp)
        )
    }
}