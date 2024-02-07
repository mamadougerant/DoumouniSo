package com.malisoftware.components.container

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.malisoftware.components.R
import com.malisoftware.components.shimmerEffect

/**
 * A Composable that represents a container with an image, optional icons, and a click listener.
 *
 * @param modifier The modifier to apply to this composable.
 * @param imageUrl The URL of the image to be displayed. If null, no image is shown.
 * @param leftIcon The @Composable function to render an icon on the bottom-left corner.
 * @param rightIcon The @Composable function to render an icon on the bottom-right corner.
 * @param topLeftIcon The @Composable function to render an icon on the top-left corner.
 * @param topRightIcon The @Composable function to render an icon on the top-right corner.
 * @param onClick The callback function to be invoked when the container is clicked.
 * @param color The background color of the container. If null, it defaults to LightGray.
 * @param shape The shape of the container. Defaults to RoundedCornerShape with a corner radius of 10.dp.
 *
 * @sample Container_
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    leftIcon: @Composable (() -> Unit)? = null,
    rightIcon: @Composable (() -> Unit)? = null,
    topLeftIcon: @Composable (() -> Unit)? = null,
    topRightIcon: @Composable (() -> Unit)? = null,
    onClick: () -> Unit = {},
    color: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    loading: Boolean = false,
    enabled: Boolean = true,
    closeText: String = "Ferme"
) {
    // TODO: Add a error image or leave it
    val painter = rememberAsyncImagePainter(ImageRequest.Builder(LocalContext.current)
            .data(data = imageUrl)
            //.error(R.drawable.salad)
            .build())
    val colorMatrix = ColorMatrix(
        floatArrayOf(
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0.33f, 0.33f, 0.33f, 0f, 0f,
            0f, 0f, 0f, 1f, 0f
        )
    )
    val matrixDarkMode = ColorMatrix(
        floatArrayOf(
            0.7f, 0.0f, 0.0f, 0f, 0f, // Red channel
            0.0f, 0.7f, 0.0f, 0f, 0f, // Green channel
            0.0f, 0.0f, 0.7f, 0f, 0f, // Blue channel
            0f, 0f, 0f, 1f, 0f // Alpha channel (pas de changement)
        )
    )

    Box(
        modifier = modifier
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(color ?: Color.LightGray),
            elevation = CardDefaults.cardElevation(pressedElevation = 10.dp),
            onClick = if (enabled) { onClick } else {{}},
            shape = shape
        ) {
            Box {
                Image(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect(enabled = painter.state is AsyncImagePainter.State.Loading || loading),
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    colorFilter = if (enabled) {
                        if (isSystemInDarkTheme()) ColorFilter.colorMatrix(matrixDarkMode)
                        else null
                    } else ColorFilter.colorMatrix(colorMatrix)
                )
                if (!enabled) Text(
                    text = closeText,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        IconColumn(alignment = Alignment.BottomStart, content = leftIcon)
        IconColumn(alignment = Alignment.BottomEnd, content = rightIcon)
        IconColumn(alignment = Alignment.TopStart, content = topLeftIcon)
        IconColumn(alignment = Alignment.TopEnd, content = topRightIcon)
    }
}
@Composable
private fun BoxScope.IconColumn(alignment: Alignment, content: @Composable (() -> Unit)?) {
    if (content != null)
        Column(modifier = Modifier.align(alignment)) {
            content()
        }
}



@Preview
@Composable
fun Container_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

    ) {
        var text by remember{
            mutableStateOf("ImageContainer")
        }
        Text(text = text, modifier = Modifier.align(Alignment.TopCenter))
        ImageContainer(
            imageUrl = "https://news.mit.edu/sites/default/files/styles/news_article__image_gallery/public/images/202312/MIT_Food-Diabetes-01_0.jpg?itok=Mp8FVJkC",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp),
            enabled = true,
            onClick = { text = "Clicked" },

        )
    }
}