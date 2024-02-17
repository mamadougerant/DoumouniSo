package com.malisoftware.components.LazyLists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.CategoryContainer
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.model.CategoryData
import com.malisoftware.theme.DoumouniDronTheme
import kotlin.random.Random


@Composable
fun CategoryList(
    modifier: Modifier = Modifier,
    title: String = "Categories",
    categories: List<CategoryData>,
    onClick: (CategoryData) -> Unit = {},
    color: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    loading: Boolean = false,
    trailingContent: @Composable () -> Unit = {},
) {
    val hapticFeedback = LocalHapticFeedback.current
    RowListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
        loading = loading,
    ){
        items(categories.size) {
            CategoryContainer(
                modifier = Modifier,
                title = categories[it].title,
                imageUrl = categories[it].imageUrl,
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick.invoke(categories[it])
                },
                color = color,
                shape = shape,
                loading = loading,
            )
        }
    }
}

@Composable
fun RoundedCategoryList(
    modifier: Modifier = Modifier,
    categories: List<CategoryData>,
    onClick: (CategoryData) -> Unit = {},
    color: Color? = null,
    trailingContent: @Composable () -> Unit = {},
    title: String? = "Shops",
    ) {
    val hapticFeedback = LocalHapticFeedback.current
    RowListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
    ){
        items(categories.size) {
            CategoryContainer(
                modifier = Modifier,
                title = categories[it].title,
                imageUrl = categories[it].imageUrl,
                onClick = {
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                    onClick.invoke(categories[it])
                },
                color = color,
                shape = CircleShape,
                loading = Random.nextBoolean(),
            )
        }
    }
}

@Preview
@Composable
fun CategoryList_() {
    DoumouniDronTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center,
        ) {
            RoundedCategoryList(
                categories = listOf(
                    CategoryData(
                        imageUrl = "https://images.unsplash.com/photo-1621574539437-4b7b7b5b5b0f?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQwfHh6eWJ4Z0J0Z0lBfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
                        title = "Title"
                    ),
                    CategoryData(
                        imageUrl = "https://images.unsplash.com/photo-1621574539437-4b7b7b5b5b0f?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQwfHh6eWJ4Z0J0Z0lBfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
                        title = "Title"
                    ),
                    CategoryData(
                        imageUrl = "https://images.unsplash.com/photo-1621574539437-4b7b7b5b5b0f?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQwfHh6eWJ4Z0J0Z0lBfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
                        title = "Title"
                    ),
                    CategoryData(
                        imageUrl = "https://images.unsplash.com/photo-1621574539437-4b7b7b5b5b0f?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQwfHh6eWJ4Z0J0Z0lBfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
                        title = "Title"
                    ),
                    CategoryData(
                        imageUrl = "https://images.unsplash.com/photo-1621574539437-4b7b7b5b5b0f?ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDQwfHh6eWJ4Z0J0Z0lBfHxlbnwwfHx8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80",
                        title = "Title"
                    ),
                ),
            )
        }
    }
}