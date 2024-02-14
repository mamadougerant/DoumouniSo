package com.malisoftware.components.container

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.theme.AppTheme

@Composable
fun HorizontalItemContainer(
    modifier: Modifier = Modifier,
    customIcon: (@Composable () -> Unit)? = null,
    textsColors: Color? = null,
    title: String = "title title title",
    price: String = "price",
    quantity: Int = 0,
    description: String = "description description description description description description description",
    imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
    onClick: () -> Unit = {},
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ImageContainer(
            onClick = onClick,
            imageUrl = imageUrl,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            rightIcon = {}
        )
        TextDisposition(
            modifier = modifier
                .clickable { onClick() },
            h1 = title,
            h2 = price,
            h3 = description,
            h1Style = AppTheme.typography.titleLarge.copy(color = textsColors ?: Color.Unspecified),
            h2Style = AppTheme.typography.titleSmall.copy(color = textsColors ?: Color.Unspecified),
            h3Style = AppTheme.typography.titleSmall.copy(color = textsColors ?: Color.Unspecified),
        ) {
            if (customIcon != null) {
                customIcon()
            } else {
                IconButton(
                    onClick = onClick,
                    colors = IconButtonDefaults.iconButtonColors(Color.LightGray,Color.Black),
                    modifier = Modifier
                        .padding(5.dp)
                        .size(40.dp)
                ) {
                    if (quantity == 0)
                        Icon(Icons.Rounded.Add, contentDescription = "")
                    else Text(text = quantity.toString(), style = AppTheme.typography.titleMedium)
                }
            }
        }
    }

}

@Composable
fun HorizontalCartItemContainer(
    modifier: Modifier = Modifier,
    quantity: Int = 0,
    onQuantityChange: (Int) -> Unit = {},
    title: String = "title title title",
    price: String = "price",
    imageUrl: String = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
    onClick: () -> Unit = {},
) {

    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        ImageContainer(
            onClick = onClick,
            imageUrl = imageUrl,
            modifier = Modifier
                .width(70.dp)
                .height(70.dp),
            rightIcon = {}
        )
        TextDisposition(
            modifier = modifier
                .clickable { onClick() },
            h1 = title,
            h2 = price,
            h1Style = MaterialTheme.typography.titleMedium,
        ) {
            PlusIcon(quantity, onQuantityChange,0.dp,Color.LightGray)
        }
    }

}

@Preview
@Composable
fun HorizontalItemContainer_() {
    HorizontalItemContainer(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp)
    )
}