package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.shimmerEffect
import com.malisoftware.theme.PaddingSizes

/**
 * RowListContainer is a composable function that creates a container with a row-oriented lazy list
 * to display items horizontally. It may include a title and optional trailing content.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param title Title of the container, defaults to "Categories".
 * @param trailingContent Lambda function defining the trailing content of the container.
 * @param content Lambda function to populate the lazy list with items.
 *
 * Usage Example:
 * ```
 * RowListContainer(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .height(200.dp),
 *     title = "Featured Categories",
 *     trailingContent = { /* Custom trailing content */ }
 * ) {
 *     items(categories) { category ->
 *         // Composable item for each category
 *     }
 * }
 * ```
 * @see ColumnListContainer
 */
@Composable
fun RowListContainer(
    modifier: Modifier = Modifier,
    title: String? = "Categories",
    trailingContent: @Composable () -> Unit = {},
    loading: Boolean = false,
    content: LazyListScope.() -> Unit,
) {

    CoreListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
        loading = loading,
    ){
        LazyRow (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(PaddingSizes.medium),
        ){
            content()
        }
    }
}

/**
 * ColumnListContainer is a composable function that creates a container with a column-oriented lazy list
 * to display items vertically. It may include a title and optional trailing content.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param title Title of the container, defaults to "Categories".
 * @param trailingContent Lambda function defining the trailing content of the container.
 * @param content Lambda function to populate the lazy list with items.
 *
 * Usage Example:
 * ```
 * ColumnListContainer(
 *     modifier = Modifier
 *         .fillMaxWidth()
 *         .height(300.dp),
 *     title = "Explore Categories",
 *     trailingContent = { /* Custom trailing content */ }
 * ) {
 *     items(categories) { category ->
 *         // Composable item for each category
 *     }
 * }
 * ```
 * @see RowListContainer
 */
@Composable
fun ColumnListContainer(
    modifier: Modifier = Modifier,
    title: String? = "Categories",
    trailingContent: @Composable () -> Unit = {},
    content: LazyListScope.() -> Unit,
) {
    CoreListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
    ){
        LazyColumn (
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(PaddingSizes.medium),
        ){
            content()
        }
    }
}

@Composable
fun GridListContainer(
    modifier: Modifier = Modifier,
    title: String? = "Categories",
    trailingContent: @Composable () -> Unit = {},
    columns: Int = 3,
    content: LazyGridScope.() -> Unit,
) {
    CoreListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
    ){
        LazyVerticalGrid(
            columns = GridCells.Fixed(columns),
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(PaddingSizes.medium),
        ){
            content()
        }
    }
}

@Composable
fun CoreListContainer(
    modifier: Modifier = Modifier,
    title: String? = "Categories",
    trailingContent: @Composable () -> Unit = {},
    loading: Boolean = false,
    content: @Composable () -> Unit,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(PaddingSizes.medium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        if (title != null) {
            TextWithIcon(
                title = if (loading)"" else title,
                modifier = Modifier
                    .fillMaxWidth()
                    .shimmerEffect(
                        secondModifier = Modifier
                            .width(150.dp)
                            .height(30.dp)
                            .clip(RoundedCornerShape(50)),
                        enabled = loading
                    )
            ) { trailingContent() }
        }
        content()
    }
}