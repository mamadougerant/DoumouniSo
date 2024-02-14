package com.malisoftware.components.component.scaffold

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme

/**
 * A composable function that displays a set of tabs with content associated with each tab.
 *
 * @param list A list of pairs where each pair consists of a String representing the tab title
 *             and a composable function that provides the content for the tab.
 */
@Composable
fun ContentTabs(
    list: List<Pair<String,(@Composable () -> Unit)?> > = listOf(),
    onIndexChange: (Int) -> Unit = {},
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Black,
) {
    var selectedIndex by remember { mutableStateOf(0) }
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = selectedIndex,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedIndex])
                        .clip(RoundedCornerShape(50))
                        .width(5.dp),
                    color = Color.Black,
                    height = 2.dp,
                )
            },
            divider = {  }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    selected = selected,
                    onClick = { selectedIndex = index; onIndexChange(index) },
                    text = { Text(text = text.first, style = AppTheme.typography.titleMedium) },
                    selectedContentColor = Color.Black,
                )
            }
        }
        list[selectedIndex].second?.let { it() }
    }
}

@Composable
fun NoScrollableContentTabs(
    modifier: Modifier = Modifier,
    list: List<Pair<String,(@Composable () -> Unit)?> > = listOf(),
    onIndexChange: (Int) -> Unit = {},
    indexInitial: Int = 0,
    containerColor: Color = Color.Transparent,
    contentColor: Color = Color.Black,
    textColor: Color? = null,
) {
    var selectedIndex by remember (indexInitial) { mutableStateOf(indexInitial) }
    Column (
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TabRow(
            selectedTabIndex = selectedIndex,
            modifier = modifier
                .align(Alignment.CenterHorizontally),
            containerColor = containerColor,
            contentColor = contentColor,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier
                        .tabIndicatorOffset(tabPositions[selectedIndex])
                        .clip(RoundedCornerShape(50))
                        .width(5.dp),
                    color = textColor ?: if (isSystemInDarkTheme()) Color.White else Color.Black,
                    height = 2.dp,
                )
            },
            divider = {  }
        ) {
            list.forEachIndexed { index, text ->
                val selected = selectedIndex == index
                Tab(
                    selected = selected,
                    onClick = { selectedIndex = index; onIndexChange(index) },
                    text = { Text(text = text.first, style = AppTheme.typography.titleMedium) },
                    selectedContentColor = textColor ?: if (isSystemInDarkTheme()) Color.White else Color.Black,
                )
            }
        }
        list[selectedIndex].second?.let { it() }
    }
}