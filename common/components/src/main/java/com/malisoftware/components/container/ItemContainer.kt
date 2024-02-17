package com.malisoftware.components.container

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.R
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.ButtonSizes
import com.malisoftware.theme.CardSizes.plusIconHeight
import com.malisoftware.theme.CardSizes.shopItemHeight
import com.malisoftware.theme.CardSizes.shopItemWidth
import com.malisoftware.theme.PaddingSizes

/**
 * CoreItemContainer is a composable function that creates a container for representing items,
 * such as products and services, with an image, title, subtitle, and optional icons for user interaction.
 * @param modifier Modifier for styling and layout adjustments.
 * @param imageUrl URL of the image representing the item.
 * @param onClick Lambda function to be executed when the item container is clicked.
 * @param color Background color of the item container.
 * @param shape Shape of the container, default is [RoundedCornerShape] with a radius of 10.dp.
 * @param quantity The quantity of the item.
 * @param onQuantityChange Lambda function to handle quantity change events, receives an Int indicating the new quantity.
 * @param topLeftIcon Optional composable lambda function to define content for the top-left corner of the container.
 *
 * @see ImageContainer
 */

@Composable
fun CoreItemContainer(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    onClick: () -> Unit = {},
    color: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    quantity: Int = 0,
    onQuantityChange: (Int) -> Unit = {},
    showButton: Boolean = true,
    topLeftIcon: @Composable (() -> Unit)? = null,
) {
    ImageContainer(
        modifier = modifier,
        onClick = onClick,
        imageUrl = imageUrl,
        color = color,
        shape = shape,
        rightIcon = { if (showButton) PlusIcon(quantity, onQuantityChange) else {} },
        topLeftIcon = topLeftIcon
    )
}


/**
 * Composable function to display a Item container with an image (width(115.dp),height(130.dp)) and title
 * with MaterialTheme.typography.titleMedium and MaterialTheme.typography.titleSmall.
 * @param modifier Additional modifier for customization.
 * @param title The title of the Item.
 * @param subtitle The subtitle of the Item.
 * @param imageUrl The URL for the Item image.
 * @param onClick Callback for click events on the Item.
 * @param color Background color for the container.
 * @param shape Shape of the container, default is RoundedCornerShape.
 * @param quantity The quantity of the Item.
 * @param onQuantityChange Callback for quantity change events on the Item.
 * @param topLeftIcon Optional composable lambda function to define content for the top-left corner of the container.
 *
 * @see CoreItemContainer
 * @sample ItemContainer_
*/
@Composable
fun ItemContainer(
    modifier: Modifier = Modifier,
    title: String = "Title",
    subtitle: String = "Subtitle",
    imageUrl: String? = null,
    onClick: () -> Unit = {},
    color: Color? = null,
    textsColor: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    showAddButton: Boolean = true,
    quantity: Int = 0,
    onQuantityChange: (Int) -> Unit = {},
    topLeftIcon: @Composable (() -> Unit)? = null,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingSizes.small),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        CoreItemContainer(
            modifier = Modifier
                .width(shopItemWidth)
                .height(shopItemHeight),
            imageUrl = imageUrl,
            onClick = onClick,
            color = color,
            shape = shape,
            quantity = quantity,
            onQuantityChange = onQuantityChange,
            topLeftIcon = topLeftIcon,
            showButton = showAddButton,
        )
        TextDisposition(
            modifier = Modifier
                .width(shopItemWidth)
                .padding(start = PaddingSizes.medium),
            h1 = title,
            h1Style = AppTheme.typography.titleMedium.copy(color = textsColor ?: Color.Unspecified),
            h2 = subtitle,
            h2Style = AppTheme.typography.titleSmall.copy(color = textsColor ?: Color.Unspecified),
        )
    }
}


@Composable
fun PlusIcon(
    quantity: Int = 0,
    onQuantityChange: (Int) -> Unit = {},
    elevation: Dp = 10.dp,
    color: Color = Color.White,
) {
    Card (
        modifier = Modifier
            .padding(horizontal = PaddingSizes.small, vertical = PaddingSizes.medium)
            .height(plusIconHeight),
        shape = CircleShape,
        colors = CardDefaults.cardColors(color),
        elevation = CardDefaults.cardElevation(elevation),
    ) {
        AnimatedPlusMinusIcon(
            quantity = quantity,
            onQuantityChange = onQuantityChange,
        )
    }
}

@Composable
private fun ColumnScope.AnimatedPlusMinusIcon(
    quantity: Int = 0,
    onQuantityChange: (Int) -> Unit = {},
) {
    var num by remember(quantity) { mutableStateOf(quantity.coerceAtLeast(0)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PaddingSizes.small),
        modifier = Modifier
            .padding(horizontal = PaddingSizes.small)
            .fillMaxHeight()
            .align(Alignment.CenterHorizontally)
    ) {
        AnimatedVisibility(visible = num > 0) {
            Log.d("ShopItem", "num: $num")
            IconButton(
                onClick = { if (num in 1..9 ) { num-- ; onQuantityChange(num) }  },
                colors = IconButtonDefaults.iconButtonColors(Color.Unspecified, Color.Black),
                modifier = Modifier.size(ButtonSizes.small)
            ) {
                if (num > 1) Icon(painterResource(id = R.drawable.ic_baseline_minus), contentDescription = "")
                else Icon(Icons.Rounded.Delete, contentDescription = "")
            }
        }
        AnimatedVisibility(visible = num > 0) {
            Text(text = num.toString(), style = AppTheme.typography.titleMedium,color = Color.Black)
        }
        IconButton(
            onClick = { if (num in 0..8 ) { num++; onQuantityChange(num) } },
            colors = IconButtonDefaults.iconButtonColors(Color.Unspecified, Color.Black),
            modifier = Modifier.size(ButtonSizes.small)
        ) {
            Icon(Icons.Rounded.Add, contentDescription = "")
        }
    }
}

@Preview
@Composable
fun ItemContainer_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

        ) {
        ItemContainer()
    }
}