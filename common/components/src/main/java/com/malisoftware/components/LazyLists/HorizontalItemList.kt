package com.malisoftware.components.LazyLists

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.container.HorizontalCartItemContainer
import com.malisoftware.components.container.HorizontalItemContainer
import com.malisoftware.model.Items
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme

fun LazyListScope.HorizontalItemList(
    modifier: Modifier = Modifier,
    items: List<Items> = emptyList(),
    onClick: (Items) -> Unit = {},
    title: String = "title",
    customIcon: (@Composable () -> Unit)? = null,
    color: Color? = null ,
    textsColor: Color? = null,
) {

    item {
        Card(
            modifier = Modifier
                .fillMaxWidth() ,
            colors = CardDefaults.cardColors(color ?: if (isSystemInDarkTheme()) AppTheme.colors.background else Color.White),
            shape = RoundedCornerShape(0.dp),
        ) {
            if (title.isNotEmpty()) {
                TextWithIcon(modifier = Modifier.padding(10.dp), title = title) {}
            }
            items.forEachIndexed { index, item ->
                HorizontalItemContainer(
                    title = item.title,
                    price = item.formattedPrice,
                    imageUrl = item.imageUrl,
                    description = item.description,
                    quantity = item.quantity,
                    onClick = { onClick.invoke(item)},
                    modifier = modifier,
                    customIcon = customIcon,
                    textsColors = textsColor,
                )
                if (index != items.size - 1) {
                    Divider(Modifier.padding(vertical = 10.dp))
                }
            }
        }

    }


}

fun LazyListScope.HorizontalCartItemList(
    modifier: Modifier = Modifier,
    items: List<Items> = emptyList(),
    onQuantityChange: (Int,Items) -> Unit = {_,_->},
    onClick: (Items) -> Unit = {},
) {

    item {
        items.forEachIndexed { index, item ->
            HorizontalCartItemContainer(
                title = item.title,
                price = formatPrice(item.price * item.quantity),
                imageUrl = item.imageUrl,
                onClick = { onClick.invoke(item)},
                modifier = modifier,
                quantity = item.quantity,
                onQuantityChange = { onQuantityChange.invoke(it,item) },
            )
            if (index != items.size - 1) {
                Divider(Modifier.padding(vertical = 10.dp))
            }
        }
    }


}

@Preview
@Composable
fun HorizontalItemList_() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
        ,
    ){
        HorizontalCartItemList(
            items = List(30){ Items() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)

        )
    }

}