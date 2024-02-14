package com.malisoftware.components.LazyLists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.container.ItemContainer
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme

@Composable
fun ItemList(
    modifier: Modifier = Modifier,
    title: String? = "Boissons",
    items: List<Items>,
    onClick: (Items) -> Unit = {},
    onQuantityChange: (Items, Int) -> Unit = { items: Items, i: Int -> },
    color: Color? = null,
    showAddButton: Boolean = true,
    isShopItems: Boolean = false,
    textsColor: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    trailingContent: @Composable (List<Items>) -> Unit = {},
) {
    if (items.isEmpty()) return
    RowListContainer(
        modifier = modifier,
        title = title,
        trailingContent = { trailingContent.invoke(items) },
    ) {
        items(items.size) {item->
            ItemContainer(
                modifier = Modifier,
                title = items[item].title,
                subtitle = items[item].formattedPrice + if (isShopItems) "\n" + items[item].description else "",
                imageUrl = items[item].imageUrl,
                onClick = { onClick.invoke(items[item]) },
                color = color,
                shape = shape,
                textsColor = textsColor,
                showAddButton = showAddButton,
                quantity = items[item].quantity,
                onQuantityChange = { onQuantityChange.invoke(items[item].copy(quantity = it),it) },
            )
        }
    }
}


fun LazyListScope.GridItemList(
    modifier: Modifier = Modifier,
    title: String? = "Boissons",
    items: List<Items>,
    onClick: (Items) -> Unit = {},
    onQuantityChange: (Items,Int) -> Unit = { items: Items, i: Int -> },
    color: Color? = null,
    showAddButton: Boolean = true,
    isShopItems: Boolean = false,
    textsColor: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    trailingContent: @Composable (List<Items>) -> Unit = {},
) {
    if (items.isEmpty()) return
    val gridItems = items.chunked(3)
    item {
        TextWithIcon(
            title = title!!,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .padding(horizontal = 10.dp),
            customStyle = AppTheme.typography.titleLarge.copy(color = textsColor ?: Color.Unspecified )
        ){ trailingContent.invoke(items) }
    }
    items(gridItems.size) {item->
        val gridItem = gridItems[item]
        ItemList(
            modifier = modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            title = null,
            items = gridItem,
            onClick = onClick,
            onQuantityChange = onQuantityChange,
            color = color,
            showAddButton = showAddButton,
            isShopItems = isShopItems,
            shape = shape,
            textsColor = textsColor,
            trailingContent = trailingContent,
        )
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