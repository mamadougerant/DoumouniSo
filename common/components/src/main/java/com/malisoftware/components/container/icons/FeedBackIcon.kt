package com.malisoftware.components.container.icons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme

@Composable
fun FeedBackIcon(
    onClick: () -> Unit = {},
    text: String = "4.4",
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .size(30.dp),
        colors = IconButtonDefaults.iconButtonColors(
            Color.LightGray,
            contentColor = Color.Black
        )
    ) {
        Text(text = text, style = AppTheme.typography.titleSmall)
    }
}