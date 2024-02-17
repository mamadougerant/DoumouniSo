package com.malisoftware.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.malisoftware.theme.DoumouniDronTheme
import com.malisoftware.components.LazyLists.GridSmallContainerWithTitle
import com.malisoftware.components.LazyLists.SearchQueriesList
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.container.ImageContainer
import com.malisoftware.model.BusinessData
import com.malisoftware.theme.AppTheme

@Composable
fun SearchContent(
    modifier: Modifier = Modifier,
    onSearchResultClick: (String) -> Unit = {},
    query: String = "",
    viewModel: SearchViewModel = hiltViewModel(),
    isRestaurant: Boolean = true,
    onClick: (BusinessData) -> Unit = {}
) {

    Log.d("RestaurantSearchContent", "RestaurantSearchContent: $query")

    LaunchedEffect(key1 = query ){
        viewModel.search(query)
        viewModel.getRecentSearch()
        viewModel.getRestaurantCategoryList()
        viewModel.getShopCategoryList()
        viewModel.searchShop(query)
    }

    val results by (if (isRestaurant) viewModel.restaurantSearchResult
    else viewModel.shopSearchResult).collectAsState()

    val recent by (if (isRestaurant) viewModel.restaurantRecentSearch
    else viewModel.shopRecentSearch).collectAsState()

    val searchQuery by ( if (isRestaurant) viewModel.restaurantSearchQuery
    else viewModel.shopSearchQuery).collectAsState()

    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier ,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){

        items(results.size){ position ->
            val result = results[position]
            SearchResultContainer(
                businessData = result,
                onClick = {
                    onClick.invoke(it)
                    viewModel.saveSearchQuery(it.title)
                }
            )
        }
        if (results.isEmpty()) {
            SearchQueriesList(
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, "", tint = Color.Black)
                },
                onClick = { onSearchResultClick(it) },
                searchText = searchQuery,
                title = ""
            )
            item {
                GridSmallContainerWithTitle(
                    title = "Recent Searches",
                    list = recent,
                    onClick = { onSearchResultClick(it) }
                )
            }
        }


    }

}

@Composable
fun SearchResultContainer(
    modifier: Modifier = Modifier,
    businessData: BusinessData = BusinessData(),
    onClick: (BusinessData) -> Unit = {}

) {
    TextWithIcon(
        title = businessData.title,
        leadingIcon = {
            ImageContainer(
                modifier = Modifier
                    .width(90.dp)
                    .height(60.dp),
                imageUrl = businessData.imageUrl,
                shape = RoundedCornerShape(10.dp),
                color = Color.White
            )
        },
        customStyle = AppTheme.typography.titleMedium.copy(color = Color.Black),
        modifier = modifier.fillMaxWidth(),

    ) {
        IconButton(onClick = { onClick(businessData) }) {
            Icon(Icons.Default.ArrowOutward, null , tint = Color.Black)
        }
    }
}

@Preview
@Composable
fun RestaurantSearchContent_() {
    DoumouniDronTheme {
        SearchResultContainer(
            businessData = BusinessData()
        )

    }
}