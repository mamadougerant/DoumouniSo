package com.malisoftware.components.container

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.CardSizes.storeHeight
import com.malisoftware.theme.PaddingSizes

@Composable
fun StoreContainer(
    modifier: Modifier = Modifier,
    imageHeight: Dp = 136.dp,
    imageUrl1: String = "",
    imageUrl2: String = "",
    text1: String = "",
    text2: String = "",
    onImage1Click: () -> Unit = {},
    onImage2Click: () -> Unit = {},
    loading: Boolean = false
) {
    val hapticFeedback: HapticFeedback = LocalHapticFeedback.current
    @Composable
    fun Images() {
        Row (
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(PaddingSizes.large),
            verticalAlignment = Alignment.CenterVertically
        ){
            ImageContainer(
                imageUrl = imageUrl1,
                modifier = Modifier.weight(1f).storeModifier(),
                topLeftIcon = { StoreText(text = text1) },
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onImage1Click()
                },
                loading = loading,

            )

            ImageContainer(
                imageUrl = imageUrl2,
                modifier = Modifier.weight(1f).storeModifier(),
                topLeftIcon = { StoreText(text = text2) },
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onImage2Click()
                },
                loading = loading,
            )
        }
    }
    Card (
        modifier = Modifier
            .fillMaxWidth(),
        shape = AppTheme.shapes.extraSmall,
        colors = CardDefaults.cardColors(AppTheme.colors.surface)
    ) {
        Images()
    }

}

@Composable
fun StoreText(
    text: String,
) {
    Text(
        text = text,
        style = AppTheme.typography.titleLarge,
        modifier = Modifier.padding(PaddingSizes.small)
    )
}



fun Modifier.storeModifier() : Modifier = composed {
    this
        .height(storeHeight)
        .border(PaddingSizes.tiny, Color.LightGray.copy(0.4f), AppTheme.shapes.medium)
}


@Preview
@Composable
fun StoreContainer_() {
    StoreContainer(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        imageUrl2 = "https://imageproxy.wolt.com/venue/6232faaafd94b762808350de/ebcc50b6-a903-11ec-8d84-92cd96cd4d02_habib3.jpg",
        text1 = "Restaurant",
        text2 = "Market",
        imageUrl1 = "https://news.mit.edu/sites/default/files/styles/news_article__image_gallery/public/images/202312/MIT_Food-Diabetes-01_0.jpg?itok=Mp8FVJkC"
    )
}