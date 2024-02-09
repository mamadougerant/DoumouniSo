package com.malisoftware.components.component.scaffold

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.component.CustomSearchBar


/**
 * CustomScaffold is a composable function that creates a custom scaffold with a scrollable top app bar,
 * supporting various customization options such as a background image, dynamic bar color changes,
 * and additional content in the top app bar based on scroll position.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param text Title text to be displayed in the top app bar.
 * @param imageUrl URL of the image to be displayed at the top of the scaffold.
 * @param scrollState LazyListState used to track the scroll position.
 * @param offset Offset value to trigger color changes in the top app bar.
 * @param barColor Background color of the top app bar.
 * @param navIcon Lambda function defining the navigation icon.
 * @param onNavIconClick Lambda function to be executed when the navigation icon is clicked.
 * @param onHeartClick Lambda function to be executed when the heart icon is clicked.
 * @param barExtraContent Lambda function defining extra content in the top app bar based on scroll position.
 * @param showBarAtIndex Index at which to show extra content in the top app bar.
 * @param content Lambda function defining the content of the scaffold.
 *
 * @sample CustomScafold_
 * @see CustomTopBar for the customizable top app bar implementation.
 * @see ContentTabs for the tab implementation.
 * @see BusinessScreenScaffold for the search bar implementation.
 */
@Composable
fun CustomScaffold(
    modifier: Modifier = Modifier,
    text: String = "ScrollableTopAppBar",
    imageUrl: String = "",
    scrollState: LazyListState = rememberLazyListState(),
    offset: Int = 270,
    barColor: Color = Color.White,
    navIcon: (@Composable () -> Unit)? = null,
    onNavIconClick: () -> Unit = {},
    isFavorite: Boolean = false,
    onSearch: () -> Unit = {},
    onHeartClick: (Boolean) -> Unit = {},
    barExtraContent: (@Composable () -> Unit)? = null,
    showBarAtIndex: Int? = 2,
    contentPaddingValues: (PaddingValues)->Unit = {  },
    content: LazyListScope.() -> Unit = {},
) {
    CoreScaffold(
        modifier = modifier,
        topBar = {
            CustomTopBar(
                text = text,
                scrollState = scrollState,
                offset = offset,
                barColor = barColor,
                navIcon = navIcon,
                onNavIconClick = onNavIconClick,
                onSearch = onSearch,
                onHeartClick = onHeartClick,
                barExtraContent = barExtraContent,
                showBarAtIndex = showBarAtIndex,
                isFavorite = isFavorite
            )
        },
        imageUrl = imageUrl,
        scrollState = scrollState,
        contentPaddingValues = contentPaddingValues,
        content = content
    )
}








@Preview
@Composable
fun CustomScafold_() {
    val scrollState = rememberLazyListState()
    var extraText by remember { mutableStateOf("Extra Text") }
    var showSearchResult by remember { mutableStateOf(false) }
    var paddingValues by remember { mutableStateOf(PaddingValues()) }
    Box {
        CustomScaffold(
            scrollState = scrollState,
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
            barExtraContent = {
                ContentTabs(
                    list = listOf(
                        Pair("Tab 1", null),
                        Pair("Tab 2", null),
                        Pair("Tab 3", null),
                    ),
                    onIndexChange = { extraText = "Tab $it" },
                    containerColor = Color.White
                )
            },
            onSearch = { showSearchResult = true },
            contentPaddingValues = { paddingValues = it },
        ) {
            items(100) {
                Card(
                ) {
                    Text("Item $extraText $it", modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clickable {})
                }
            }
        }
        AnimatedVisibility(
            visible = showSearchResult && !scrollState.isScrollInProgress,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                CustomSearchBar(
                    modifier = Modifier.fillMaxWidth(),
                    tabList = listOf(
                        "Tab 1" to {
                           LazyColumn(){
                               items(100) {
                                   Card(
                                   ) {
                                       Text("Item $extraText $it", modifier = Modifier
                                           .fillMaxWidth()
                                           .height(56.dp)
                                           .clickable {})
                                   }
                               }
                           }
                        },
                    ),
                    activeContainerColor = Color.White,
                    onGoBack = { showSearchResult = false },
                    onQueryChange = {extraText = it},
                )
            }
        }
    }
}






