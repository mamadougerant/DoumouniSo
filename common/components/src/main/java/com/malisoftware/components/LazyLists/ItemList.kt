package com.malisoftware.components.LazyLists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.ItemContainer
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.model.Items

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    title: String? = "Boissons",
    items: List<Items>,
    onClick: (Items) -> Unit = {},
    onQuantityChange: (Items) -> Unit = {},
    color: Color? = null,
    showAddButton: Boolean = true,
    shape: Shape = RoundedCornerShape(10.dp),
    trailingContent: @Composable () -> Unit = {},
) {
    RowListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
    ) {
        items(items.size) {item->
            ItemContainer(
                modifier = Modifier,
                title = items[item].title,
                subtitle = items[item].formattedPrice,
                imageUrl = items[item].imageUrl,
                onClick = { onClick.invoke(items[item]) },
                color = color,
                shape = shape,
                showAddButton = showAddButton,
                quantity = items[item].quantity,
                onQuantityChange = { onQuantityChange.invoke(items[item].copy(quantity = it)) },
            )
        }
    }
}


@Preview
@Composable
fun ItemList_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        ItemList(
            items = listOf(
                Items(
                    title = "Coca",
                    price = 500.5,
                    imageUrl = "https://www.coca-cola-france.fr/content/dam/GO/CokeZone/France/FR/2019/10/01/hero-desktop.jpg"
                ),

            )
        )
    }
}