package com.future.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.doumounidron.theme.DoumouniDronTheme
import com.malisoftware.components.LazyLists.GridSmallContainerWithTitle
import com.malisoftware.components.LazyLists.SearchQueriesList

@Composable
fun RestaurantSearchContent(
    modifier: Modifier = Modifier,
    onSearchResultClick: (String) -> Unit = {},
    searchResult: List<String> = List(7) { "Hellovvh" },
    recentSearch: List<String> = List(7) { "Hellovvh" }
) {
    LazyColumn(
        modifier = modifier ,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        SearchQueriesList(
            leadingIcon = {
                Icon( imageVector = Icons.Default.Search,  "",tint = Color.Black )
            },
            onClick = { onSearchResultClick(it) },
            searchText = searchResult,
            title = "Recent Searches"
        )
        item{
            GridSmallContainerWithTitle(
                title = "Recent Searches",
                list = recentSearch,
                onClick = { onSearchResultClick(it) }
            )
        }

    }

}

@Preview
@Composable
fun RestaurantSearchContent_() {
    DoumouniDronTheme {
        RestaurantSearchContent(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        )

    }
}