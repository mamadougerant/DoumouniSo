package com.malisoftware.components.container.icons

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme

@Composable
fun SmallLeftIcon(
    modifier: Modifier = Modifier,
    text: String = "4 min",
    color: Color = Color.White,
    textColor: Color = Color.White,
    shape: Shape = CircleShape
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(color),
        shape = shape,
    ) {
        Text(
            text = text,
            style = AppTheme.typography.labelSmall,
            modifier = Modifier.padding(5.dp),
            color = textColor
        )
    }
}