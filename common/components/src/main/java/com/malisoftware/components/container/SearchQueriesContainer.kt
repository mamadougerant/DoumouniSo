package com.malisoftware.components.container

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.HorizontalDividerSizes
import com.malisoftware.theme.PaddingSizes

/**
 * SearchQueriesContainer is a composable function that creates a container for search queries.
 * It consists of a row with an optional leading icon, a text representing the query, and an optional trailing icon.
 *
 * @param leadingIcon Lambda function defining the leading icon.
 * @param trailingIcon Lambda function defining the trailing icon.
 * @param onClick Lambda function to be executed when the container is clicked.
 * @param text Text representing the search query.
 *
 * @sample SearchQueriesContainer_
 */
@Composable
fun SearchQueriesContainer(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {},
    text: String = "example",
    showDivider: Boolean = true,
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(start = PaddingSizes.Dp5),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ){
        leadingIcon?.invoke()
        Column(
            modifier = Modifier.clickable { onClick() }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(PaddingSizes.Dp10),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = text,
                    style = AppTheme.typography.titleMedium,
                    modifier = Modifier.weight(1f),
                    maxLines = 1,
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis,
                )
                trailingIcon?.invoke()
            }
            if (showDivider)
                HorizontalDivider(
                    modifier = Modifier
                        .testTag("SearchQueriesContainerDivider")
                        .height(HorizontalDividerSizes.small)
                        .clip(CircleShape)
                        .padding(start = PaddingSizes.Dp10),

                    color = Color.Black
                )
        }
    }

}

@Preview
@Composable
fun SearchQueriesContainer_() {
    SearchQueriesContainer(
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search,  "" )
        },
        trailingIcon = {

            Icon(imageVector = Icons.Default.Clear,  "" )

        },
        onClick = {},
        text = "examebgnffgngggbb",
    )
}
