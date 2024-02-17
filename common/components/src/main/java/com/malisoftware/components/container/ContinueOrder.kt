package com.malisoftware.components.container

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.icons.ArrowForward
import com.malisoftware.theme.PaddingSizes

@Composable
fun ContinueOrder(
    imageUrl: String,
    title: String,
    subtitle: String,
    arrowForward: () -> Unit = {},
    text: String = "Panier",
) {
    TextWithIcon(
        title = text,
        modifier = Modifier.fillMaxWidth()
    ) {}
    OutlinedCard() {
        SmallBusinessContainer(
            modifier = Modifier.padding(PaddingSizes.small),
            imageUrl = imageUrl,
            title =  title,
            subtitle = subtitle,
        ) {
            ArrowForward(onClick = { arrowForward() })
        }
    }
}