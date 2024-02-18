package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.CardSizes.roundedBusinessPic
import com.malisoftware.theme.PaddingSizes

/**
 * SmallBusinessContainer is a composable function that creates a compact container for representing Dp30 businesses.
 * It includes an image, title, subtitle, and optional trailing content.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param imageUrl URL of the image representing the business.
 * @param title Title of the business.
 * @param subtitle Subtitle or secondary information about the business.
 * @param trailingContent Lambda function defining the trailing content of the container.
 *
 * @sample SmallBusinessContainer_
 *
 * @see TextDisposition for displaying text content.
 * @see ImageContainer for displaying images within the container.
 */
@Composable
fun SmallBusinessContainer(
    modifier: Modifier = Modifier,
    imageUrl: String? = null,
    title: String = "tiiii",
    subtitle: String = "dggdgdg",
    trailingContent: @Composable () -> Unit = {},
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(PaddingSizes.Dp10),
    ){
        ImageContainer(
            modifier = Modifier
                .size(roundedBusinessPic),
            imageUrl = imageUrl,
            onClick = {},
            loading = false,
            shape = CircleShape
        )
        TextDisposition (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = PaddingSizes.Dp10)
                .padding(end = PaddingSizes.Dp10),
            h1 = title,
            h1Style = AppTheme.typography.titleMedium,
            h2 = subtitle,
            h2Style = AppTheme.typography.titleSmall,
            rightContent = trailingContent,
        )
    }
}

@Preview
@Composable
fun SmallBusinessContainer_() {
    SmallBusinessContainer(
        modifier = Modifier.padding(horizontal = 10.dp),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
        trailingContent = {
            Icon(imageVector = Icons.Default.Image , "" )
        }

    )
}