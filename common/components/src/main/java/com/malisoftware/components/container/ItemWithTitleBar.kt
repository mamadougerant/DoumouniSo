package com.malisoftware.components.container

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.icons.SmallLeftIcon
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme


@Composable
fun ItemWithTitleBar(
    data: List<Items> = List(2){ Items() },
    onClick: (Items) -> Unit = {},
) {
    if (data.isEmpty()) return
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
    ){
        @Composable
        fun CustomCard(
            item: Items
        ) {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(topEnd = 0.dp, topStart =0.dp, bottomEnd = 10.dp, bottomStart = 10.dp),
                colors = CardDefaults.cardColors(AppTheme.colors.background.copy(alpha = 0.7f)),
            ){
                TextDisposition(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp),
                    h1 = item.title,
                    h3 = item.formattedPrice,
                    h1Style = AppTheme.typography.titleMedium,
                ) {}
            }
        }
        data.forEachIndexed { _, item ->
            ImageContainer(
                imageUrl = item.imageUrl,
                modifier = Modifier
                    .weight(1f)
                    .height(150.dp),
                rightIcon = { CustomCard(item = item) },
                onClick = { onClick(item) },
                topRightIcon = {
                    if (item.quantity > 0)
                        SmallLeftIcon(
                            text = item.quantity.toString(),
                            modifier = Modifier
                                .padding(5.dp)
                                .border(1.dp, Color.LightGray, CircleShape),
                            textColor = Color.Black,
                            shape = CircleShape,
                            color = Color.LightGray

                        )
                }
            )


        }
    }
}