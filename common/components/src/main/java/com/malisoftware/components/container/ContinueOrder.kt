package com.malisoftware.components.container

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.icons.ArrowForward

@Composable
fun ContinueOrder(
    imageUrl: String,
    title: String,
    subtitle: String,
    arrowForward: () -> Unit = {},
    text: String = "Continue Order",
) {
    TextWithIcon(
        title = text,
        modifier = Modifier.fillMaxWidth()
    ) {}
    OutlinedCard() {
        SmallBusinessContainer(
            modifier = Modifier.padding(5.dp),
            imageUrl = imageUrl,
            title =  title,
            subtitle = subtitle,
        ) {
            ArrowForward(onClick = { arrowForward() })
        }
    }
}