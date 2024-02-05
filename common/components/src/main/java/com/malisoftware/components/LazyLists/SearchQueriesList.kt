package com.malisoftware.components.LazyLists

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.malisoftware.components.container.SearchQueriesContainer
import com.malisoftware.theme.AppTheme


fun LazyListScope.SearchQueriesList(
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    onClick: (String) -> Unit = {},
    title: String = "title",
    searchText: List<String> = listOf("example"),
) {
    items(searchText.size) {
        if (it == 0) {
            Text(
                text = title,
                style = AppTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.Black,
            )
        }
        SearchQueriesContainer(
            modifier = modifier,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            onClick = { onClick(searchText[it]) },
            text = searchText[it],
        )
    }

}

@Preview
@Composable
fun SearchQueriesContainer_() {
    LazyColumn {
        SearchQueriesList(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, "")
            },

            onClick = {},
            searchText = List(30) { "example$it" }
        )
    }
}