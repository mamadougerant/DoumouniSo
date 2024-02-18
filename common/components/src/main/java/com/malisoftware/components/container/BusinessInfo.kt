package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.malisoftware.components.TextWithIcon
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.CardSizes.restaurantDeliveryTimeCard
import com.malisoftware.theme.PaddingSizes

@Composable
fun BusinessInfo(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    title: String = "Restaurant",
    subtitle: String = "Subtitle",
    subInCard1: String = "subInCard1",
    subInCard2: String = "subInCard2",
    subInCard2Color: Color = Color.Unspecified,
    subInCard1Color: Color = Color.Unspecified

) {
    Column(
        modifier = modifier
    ) {
        TextWithIcon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = PaddingSizes.Dp20),
            title = title
        ) {}
        Text(
            text = subtitle,
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = PaddingSizes.Dp20),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PaddingSizes.Dp20, vertical = PaddingSizes.Dp10),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(PaddingSizes.Dp10),
        ) {
            BusinessInfoCard(
                text = subInCard1,
                color = subInCard1Color
            )
            BusinessInfoCard(
                text = subInCard2,
                color = subInCard2Color
            )
        }
    }

}

@Composable
fun RowScope.BusinessInfoCard(
    text: String,
    color: Color,
) {
    OutlinedCard(
        modifier = Modifier
            .weight(1f)
            .height(restaurantDeliveryTimeCard),
        colors = CardDefaults.cardColors(color),

        ) {
        Text(
            text = text,
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterHorizontally)
                .padding(PaddingSizes.Dp5),
            textAlign = TextAlign.Center
        )
    }
}