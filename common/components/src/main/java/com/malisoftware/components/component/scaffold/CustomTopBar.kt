package com.malisoftware.components.component.scaffold

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.malisoftware.components.icons.HeartIcon
import com.malisoftware.components.icons.NavigationIcon
import com.malisoftware.theme.AppTheme

/**
 * CustomTopBar is a composable function that creates a customizable top app bar for use in the CustomScaffold.
 * It supports various customization options such as dynamic color changes, extra content based on scroll position,
 * and a search bar with animation.
 *
 * @param text Title text to be displayed in the top app bar.
 * @param scrollState LazyListState used to track the scroll position.
 * @param offset Offset value to trigger color changes in the top app bar.
 * @param barColor Background color of the top app bar.
 * @param navIcon Lambda function defining the navigation icon. Default is the back arrow icon.
 * @param onNavIconClick Lambda function to be executed when the navigation icon is clicked. valid only if [navIcon] is null. use [Icon].
 * @param onHeartClick Lambda function to be executed when the heart icon is clicked. valid only if [actions] is null.
 * @param barExtraContent Lambda function defining extra content in the top app bar based on scroll position.
 * @param showBarAtIndex Index at which to show extra content in the top app bar.
 * @param actions Lambda function defining the actions in the top app bar.
 * @param onSearch Lambda function to be executed when the search icon is clicked. valid only if [actions] is null.
 *
 * @see CustomScaffold for the scaffold implementation.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopBar(
    modifier: Modifier = Modifier,
    text: String = "ScrollableTopAppBar",
    scrollState: LazyListState = rememberLazyListState(),
    offset: Int = 345,
    barColor: Color = AppTheme.colors.background,
    navIcon: (@Composable () -> Unit)? = null,
    onNavIconClick: () -> Unit = {},
    isFavorite: Boolean = false,
    onSearch: () -> Unit = {},
    onHeartClick: (Boolean) -> Unit = {},
    barExtraContent: (@Composable () -> Unit)? = null,
    actions: (@Composable (Color) -> Unit)? = null,
    extraActions: (@Composable (Color) -> Unit)? = null,
    showBarAtIndex: Int? = 2,
) {
    val firstItemOffset by remember { derivedStateOf { scrollState.firstVisibleItemScrollOffset } }
    val firstItemIndex by remember { derivedStateOf { scrollState.firstVisibleItemIndex } }
    val color = if (firstItemOffset in 0..offset && firstItemIndex.dp == 0.dp) Color.Transparent
    else  if (firstItemOffset in offset..offset+60 && firstItemIndex.dp == 0.dp) AppTheme.colors.background.copy(alpha = 0.5f)
    else  if (firstItemOffset in offset..offset+120 && firstItemIndex.dp == 0.dp) AppTheme.colors.background.copy(alpha = 0.7f)
    else  if (firstItemOffset in offset..offset+180 && firstItemIndex.dp == 0.dp) AppTheme.colors.background.copy(alpha = 0.9f)
    else barColor



    @Composable
    fun Actions() {
        Row (
            verticalAlignment = Alignment.CenterVertically,
        ) {
            AnimatedVisibility (extraActions != null && color != barColor) {
                extraActions?.invoke(if (color == Color.Transparent) Color.LightGray else color,)
            }
            AnimatedVisibility (color != barColor) {
                HeartIcon(
                    onClick = onHeartClick,
                    size = 40.dp,
                    color = if (color == Color.Transparent) Color.LightGray else color,
                    isFavorite = isFavorite
                )
            }
            IconButton(
                onClick = onSearch,
                colors = IconButtonDefaults.iconButtonColors(
                    if (color == Color.Transparent) Color.LightGray else color,
                    Color.Black
                )
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "")
            }
        }
    }


    Column {
        CenterAlignedTopAppBar(
            modifier = modifier,
            title = {
                if (color == barColor) Text(text, style = AppTheme.typography.titleLarge,color= Color.Black)
            },
            navigationIcon = { NavigationIcon(
                onClick = onNavIconClick,
                navIcon = navIcon,
                color = if (color == Color.Transparent) Color.LightGray else color,
                containerColor = Color.Black
            ) },
            actions =  { actions?.invoke(color) ?: Actions() },
            colors = TopAppBarDefaults.topAppBarColors(color),
            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        )
        AnimatedVisibility(
            if (showBarAtIndex == null) false else (firstItemIndex.dp > showBarAtIndex.dp),
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            barExtraContent?.invoke()
        }

        Log.d("CustomTopBar", "firstItemOffset: $firstItemIndex")
        Log.d("CustomTopBar", "showBarAtIndex: $showBarAtIndex")
    }



}
