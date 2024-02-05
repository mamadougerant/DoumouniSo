package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.theme.AppTheme

@Composable
fun BusinessInfo(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    title: String = "Restaurant",
    subtitle: String = "Subtitle",
    subInCard1: String = "subInCard1",
    subInCard2: String = "subInCard2",
) {
    Column(
        modifier = modifier
    ) {
        TextWithIcon(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp),
            title = title
        ) {}
        Text(
            text = subtitle,
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            OutlinedCard(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                colors = CardDefaults.cardColors(Color.Unspecified),
            ) {
                Text(
                    text = "Temps de livraison     " + subInCard1,
                    style = AppTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    textAlign = TextAlign.Center
                )
            }
            OutlinedCard(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                colors = CardDefaults.cardColors(Color.Unspecified),
            ) {
                Text(
                    text = subInCard2,
                    style = AppTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxHeight()
                        .align(Alignment.CenterHorizontally)
                        .padding(5.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }

}