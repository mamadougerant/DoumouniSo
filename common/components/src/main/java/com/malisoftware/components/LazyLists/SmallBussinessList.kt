package com.malisoftware.components.LazyLists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.components.container.icons.ArrowForward
import com.malisoftware.model.Items

@Composable
fun SmallBusinessList(
    modifier: Modifier = Modifier,
    items: List<Items>,
    onClick: (Items) -> Unit = {},
    onForward: () -> Unit = {},
    color: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    imageUrl: String = "",
    title: String = "Boissons",
    subtitle: String = "null",
    trailingContent: @Composable () -> Unit = {},
    onQuantityChange: (Items) -> Unit = {},
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SmallBusinessContainer(
            modifier = Modifier.padding(10.dp),
            imageUrl = imageUrl,
            title = title,
            subtitle = subtitle,
            trailingContent = { ArrowForward(onClick = onForward) },
        )
        ItemList(
            modifier = Modifier,
            title = null,
            items = items,
            onClick = onClick,
            color = color,
            shape = shape,
            trailingContent = trailingContent,
            onQuantityChange = onQuantityChange,
        )
    }
}

@Preview
@Composable
fun SmallBusinessList_() {
    SmallBusinessList(
        items = listOf(
            Items(
                title = "Fanta",
                price = 500.00,
                imageUrl = "https://www.coca-cola-france.fr/content/dam/GO/CokeZone/France/FR/2019/10/01/hero-desktop.jpg"
            ),
            Items(
                title = "Sprite",
                price = 500.00,
                imageUrl = "https://www.coca-cola-france.fr/content/dam/GO/CokeZone/France/FR/2019/10/01/hero-desktop.jpg"
            ),
            Items(
                title = "Coca",
                price = 500.00,
                imageUrl = "https://www.coca-cola-france.fr/content/dam/GO/CokeZone/France/FR/2019/10/01/hero-desktop.jpg"
            ),
            Items(
                title = "Sprite",
                price = 500.00,
                imageUrl = "https://www.coca-cola-france.fr/content/dam/GO/CokeZone/France/FR/2019/10/01/hero-desktop.jpg"
            ),
            Items(
                title = "Coca",
                price = 500.00,
                imageUrl = "https://www.coca-cola-france.fr/content/dam/GO/CokeZone/France/FR/2019/10/01/hero-desktop.jpg"
            ),
        )
    )
}