package com.malisoftware.components.container.icons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme

@Composable
fun OutlinedButtonWithText(
    text: String,
    onClick: () -> Unit = { }
) {
    val gradientBrush = Brush.linearGradient(
        colors = listOf(Color.LightGray, Color.Black, Color.White, Color.LightGray),
        start = Offset(0f, 0f),
        end = Offset(1000f, 1000f)
    )
    val hapticFeedback = LocalHapticFeedback.current
    OutlinedButton(
        modifier = Modifier
            .height(35.dp),
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Unspecified,
            contentColor = AppTheme.colors.onBackground
        ),
        contentPadding = PaddingValues(2.dp),
        border = BorderStroke(2.dp, gradientBrush),

    ) {
        Text(
            text = text,
            style = AppTheme.typography.titleSmall,
            modifier = Modifier.padding(horizontal = 10.dp),
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
    }
}