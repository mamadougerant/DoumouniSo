package com.malisoftware.components.container.icons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun HeartIcon(
    onClick: (Boolean) -> Unit = {},
    size: Dp = 30.dp,
    color: Color = Color.LightGray
) {
    val hapticFeedback = LocalHapticFeedback.current
    var favorite by remember { mutableStateOf(false) }
    val heartIcon =if (!favorite) Icons.Default.FavoriteBorder else  Icons.Default.Favorite
    IconButton(
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            favorite = !favorite
            onClick.invoke(favorite)
        },
        modifier = Modifier
            .padding(10.dp)
            .size(size),
        colors = IconButtonDefaults.iconButtonColors(
            color,
            contentColor = Color.Black
        )
    ) {
        Icon(
            imageVector = heartIcon,
            contentDescription = "",

        )
    }
}