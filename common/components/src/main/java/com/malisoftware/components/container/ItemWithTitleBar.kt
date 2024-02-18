package com.malisoftware.components.container

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.icons.SmallLeftIcon
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.CardSizes.promotionBusinessItemBarHeight
import com.malisoftware.theme.CardSizes.promotionBusinessItemWidth
import com.malisoftware.theme.PaddingSizes


@Preview
@Composable
fun ItemWithTitleBar(
    data: List<Items> = List(2){ Items() },
    onClick: (Items) -> Unit = {},
) {
    if (data.isEmpty()) return
    LazyRow (
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingSizes.Dp10),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PaddingSizes.Dp15),
    ){
        items(data.size) {
            val item = data[it]
            ImageContainer(
                imageUrl = item.imageUrl,
                modifier = Modifier
                    .fillParentMaxWidth(0.48f)
                    .height(promotionBusinessItemWidth),
                rightIcon = { CustomCard(item = item) },
                onClick = { onClick(item) },
                topRightIcon = {
                    if (item.quantity > 0)
                        SmallLeftIcon(
                            text = item.quantity.toString(),
                            modifier = Modifier
                                .padding(PaddingSizes.Dp5)
                                .border(PaddingSizes.Dp1, Color.LightGray, CircleShape),
                            textColor = Color.Black,
                            shape = CircleShape,
                            color = Color.LightGray
                        )
                }
            )
        }
    }
}

@Composable
fun CustomCard(
    item: Items
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .height(promotionBusinessItemBarHeight),
        shape = RoundedCornerShape(topEnd = PaddingSizes.paddingO, topStart = PaddingSizes.paddingO, bottomEnd = PaddingSizes.Dp10, bottomStart = PaddingSizes.Dp10),
        colors = CardDefaults.cardColors(AppTheme.colors.background.copy(alpha = 0.7f)),
    ){
        TextDisposition(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = PaddingSizes.Dp10),
            h1 = item.title,
            h3 = item.formattedPrice,
            h1Style = AppTheme.typography.titleMedium,
        ) {}
    }
}