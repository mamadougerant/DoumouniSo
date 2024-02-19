package com.malisoftware.components.component.scaffold

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CenterFocusWeak
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.component.CustomSearchBar
import com.malisoftware.components.icons.NavigationIcon
import kotlinx.coroutines.delay
import kotlin.math.pow

/**
 * CustomScaffoldWithSearchBar is a composable function that creates a custom scaffold with a scrollable top app bar
 * and a search bar with animation.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param text Title text to be displayed in the top app bar.
 * @param imageUrl URL of the image to be displayed at the top of the scaffold.
 * @param scrollState LazyListState used to track the scroll position.
 * @param offset Offset value to trigger color changes in the top app bar.
 * @param barColor Background color of the top app bar.
 * @param navIcon Lambda function defining the navigation icon. the navigation icon is arrow back by default.
 * @param onNavIconClick Lambda function to be executed when the navigation icon is clicked.
 * @param onHeartClick Lambda function to be executed when the heart icon is clicked.
 * @param barExtraContent Lambda function defining extra content in the top app bar based on scroll position.
 * @param showBarAtIndex Index at which to show extra content in the top app bar.
 * @param contentPaddingValues Padding values for the content.
 * @param searchText Placeholder text for the search bar.
 * @param searchContent Lambda function defining the content of the search bar.
 * @param content Lambda function defining the content of the scaffold.
 *
 * @see CustomScaffoldWithSearchBar_
 * @see CustomScaffold for the scaffold implementation.
 * @see CustomSearchBar for the search bar implementation.
 * @see ContentTabs for the tab implementation.
 * */
@Composable
fun BusinessScreenScaffold(
    modifier: Modifier = Modifier,
    text: String = "ScrollableTopAppBar",
    imageUrl: String = "",
    scrollState: LazyListState = rememberLazyListState(),
    offset: Int = 270,
    barColor: Color = Color.White,
    navIcon: (@Composable () -> Unit)? = null,
    onNavIconClick: () -> Unit = {},
    onHeartClick: (Boolean) -> Unit = {},
    isFavorite: Boolean = false,
    onSearch: (String) -> Unit = {},
    barExtraContent: (@Composable () -> Unit)? = null,
    extraActionsClick: ()->Unit = {},
    showBarAtIndex: Int? = 2,
    contentPaddingValues: (PaddingValues)->Unit = {  },
    searchText: String = "Search",
    filterContents: (LazyListScope.() -> Unit)? = null,
    searchContent: LazyListScope.() -> Unit = {},
    content: LazyListScope.() -> Unit = {},
) {
    var showSearchResult by rememberSaveable { mutableStateOf(false) }
    var isBarActive by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(key1 = showSearchResult, key2 = !isBarActive){
        delay(3000)
        if (showSearchResult && !isBarActive) showSearchResult = false
    }

    Box {
        CustomScaffold(
            modifier = modifier,
            text = text,
            imageUrl = imageUrl,
            scrollState = scrollState,
            offset = offset,
            barColor = barColor,
            navIcon = navIcon,
            onNavIconClick = onNavIconClick,
            onSearch = { showSearchResult = true },
            isFavorite = isFavorite,
            onHeartClick = onHeartClick,
            barExtraContent = barExtraContent,
            showBarAtIndex = showBarAtIndex,
            contentPaddingValues = contentPaddingValues,
            content = filterContents ?: content,
            extraActions = {CustomNavIcon(extraActionsClick,it)}
        )
        AnimatedVisibility(
            visible = showSearchResult,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                CustomSearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    tabList = listOf("" to { LazyColumn(content = searchContent) } ),
                    activeContainerColor = Color.White,
                    onGoBack = { showSearchResult = false ; },
                    onQueryChange = onSearch,
                    onSearch = onSearch,
                    text = searchText,
                    onActiveChange = { isBarActive = it }
                )
            }
        }
    }
}

@Composable
fun CustomNavIcon(
    extraActionsClick: ()->Unit = {},
    tint: Color = Color.LightGray
) {
    NavigationIcon(
        navIcon = {
            Icon(imageVector = Icons.Default.CenterFocusWeak, null, tint = Color.Black )
        },
        color = tint,
        onClick = extraActionsClick
    )
}

@Preview
@Composable
fun CustomScaffoldWithSearchBar_() {
    var string by remember { mutableStateOf("Search") }
    BusinessScreenScaffold(
        scrollState = rememberLazyListState(),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
        barExtraContent = {
            ContentTabs(
                list = listOf(
                    Pair("Tab 1", null),
                    Pair("Tab 2", null),
                    Pair("Tab 3", null),
                ),
                onIndexChange = { },
                containerColor = Color.White
            )
        },

        onSearch = {
            string = it
        },
        searchContent = {
            items(100) {
                Card(
                ) {
                    Text("Item $string", modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable {})
                }
            }
        }
    ){
        items(100) {
            Card(
            ) {
                Text("Item $it", modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .clickable {})
            }
        }
    }
}