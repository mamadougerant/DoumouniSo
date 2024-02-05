package com.malisoftware.components.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malisoftware.theme.montSerrat
import kotlin.random.Random

/**
 * Composable function to display a category container with an image and title.
 *
 * @param modifier Additional modifier for customization.
 * @param title The title of the category.
 * @param imageUrl The URL for the category image.
 * @param onClick Callback for click events on the category.
 * @param color Background color for the container.
 * @param shape Shape of the container, default is RoundedCornerShape.
 *
 * @sample CategoryContainer_
 */
@Composable
fun CategoryContainer(
    modifier: Modifier = Modifier,
    title: String = "Title",
    imageUrl: String? = null,
    onClick: () -> Unit = {},
    color: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    loading: Boolean = false,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ImageContainer(
            modifier = Modifier.size(90.dp),
            onClick = onClick,
            imageUrl = imageUrl,
            color = color,
            shape = shape,
            loading = loading,
        )
        Text(
            modifier = Modifier.widthIn(90.dp),
            text = title,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp,
            fontFamily = montSerrat,
        )
    }
}

@Preview
@Composable
fun CategoryContainer_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,
    ) {
        CategoryContainer(loading = Random.nextBoolean())
    }
}
