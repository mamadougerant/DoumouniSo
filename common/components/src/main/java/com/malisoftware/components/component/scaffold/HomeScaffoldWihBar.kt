package com.malisoftware.components.component.scaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.component.CustomSearchBar

@Composable
fun HomeScaffoldWithBar(
    modifier: Modifier = Modifier,
    text: String = "ScrollableTopAppBar",
    scrollState: LazyListState = rememberLazyListState(),
    searchTabList: List<Pair<String, @Composable () -> Unit>>,
    offset: Int = 270,
    navIcon: (@Composable () -> Unit)? = null,
    actions: (@Composable (Color) -> Unit)? = null,
    onQueryChange: (String) -> Unit = {},
    searchBarActiveColor: Color = Color.White,
    onSearch: (String) -> Unit = {},
    initialQuery: String = "",
    shimmerContent: (@Composable (PaddingValues) -> Unit)? = null,
    filterContents: (LazyListScope.() -> Unit)? = null,
    content: LazyListScope.() -> Unit = {},
) {
    val firstItemOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val firstItemIndex by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    var isSearchActive by remember{ mutableStateOf(false) }
    Scaffold(
        topBar = {
            Column {
                AnimatedVisibility (
                    firstItemOffset in 0..offset && firstItemIndex.dp == 0.dp &&
                    !isSearchActive
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        navIcon?.invoke()
                        actions?.invoke(Color.Black)
                    }
                }
                CustomSearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    text = text,
                    tabList = searchTabList,
                    onQueryChange = onQueryChange,
                    activeContainerColor = searchBarActiveColor,
                    onActiveChange = { isSearchActive = it },
                    onSearch = onSearch,
                    initialQuery = initialQuery
                )
            }
        }
    ) {paddingValues ->
        if (shimmerContent != null && firstItemOffset in 0..offset && firstItemIndex.dp == 0.dp){
            shimmerContent(paddingValues)
        }else {
            LazyColumn(
                state = scrollState,
                modifier = modifier
                    .padding(paddingValues)
                    .padding(top = 10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                if (filterContents != null) filterContents()
                else content()
            }
        }
    }
}

@Preview
@Composable
fun SimpleScaffoldWithBar_(){
    HomeScaffoldWithBar(
        searchTabList = listOf(
            Pair("Tab 1", { Text(text = "Tab 1") }),
            Pair("Tab 2", { Text(text = "Tab 2") }),
            Pair("Tab 3", { Text(text = "Tab 3") }),
        ),
        navIcon = {
            Text(text = "Nav Icon")
        },
        actions = {
            Text(text = "Actions")
        },
    ){
        items(100){
            Text(text = "Item $it")
        }
    }
}