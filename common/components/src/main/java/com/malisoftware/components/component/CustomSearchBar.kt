package com.malisoftware.components.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.component.scaffold.NoScrollableContentTabs

/**
 * A composable function that creates a custom search bar with tabs.
 *
 * @param modifier Modifier for styling the search bar.
 * @param text Placeholder text for the search bar.
 * @param onSearch Callback function invoked when the search is performed.
 * @param tabList List of pairs where each pair consists of a String representing the tab title
 *                and a composable function that provides the content for the tab.
 * @see ContentTabs
 * @sample CustomSearchBar_
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchBar(
    modifier: Modifier = Modifier,
    text: String = "Search",
    onSearch: (String) -> Unit = {},
    tabList: List<Pair<String,@Composable () -> Unit>>,
    initialQuery: String = "",
    tabTextColor: Color? = null,
    activeContainerColor: Color = Color.Unspecified,
    onQueryChange: (String) -> Unit = {},
    onActiveChange: (Boolean) -> Unit = {},
    onGoBack: () -> Unit = {},
) {
    var query by remember (initialQuery) { mutableStateOf(initialQuery) }
    var active by remember{ mutableStateOf(false) }
    val containerColor = if (!active) Color.LightGray else Color.LightGray
    SearchBar(
        modifier = modifier
            .padding(horizontal = if (active) 0.dp else 10.dp),
        query = if (active) query else "",
        onQueryChange = { query = it; onQueryChange(it) },
        onSearch = onSearch,
        active = active,
        onActiveChange = { active = it; onActiveChange(it) },
        colors = SearchBarDefaults.colors(
            containerColor,
            Color.Transparent,
            TextFieldDefaults.colors(
                cursorColor = Color.Black,
                focusedTextColor = Color.Black,
                focusedTrailingIconColor = Color.Black,
                focusedPlaceholderColor = Color.Black,
                unfocusedPlaceholderColor = Color.Black,
            )
        ),
        leadingIcon = {
            SearchIcon(
                active = active,
                onClick = { active = !active; onActiveChange(active) })
        },
        placeholder = { Text(text = text, style = MaterialTheme.typography.titleSmall) },
        trailingIcon = {
            ClearIcon(onClick = {
                if (active && query.isNotEmpty()) {
                    onQueryChange("")
                    query = ""
                } else onGoBack()
            })
        },
        shape = CircleShape,
    ) {
        if (tabList.size == 1) tabList[0].second()
        else if (tabList.size < 3) NoScrollableContentTabs(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(horizontal = 20.dp),
            list = tabList,
            textColor = tabTextColor,
        )
        else ContentTabs(tabList)
    }
}

@Composable
fun SearchIcon(
    active: Boolean,
    onClick: () -> Unit = {},
) {
    if (!active) {
        IconButton(
            onClick = onClick, Modifier.size(24.dp),
            colors = IconButtonDefaults.iconButtonColors(Color.Unspecified, Color.Black),
        ) {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        }
    } else {
        IconButton(
            onClick = onClick, Modifier.size(24.dp),
            colors = IconButtonDefaults.iconButtonColors(Color.Unspecified, Color.Black),
        ) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
        }
    }
}

@Composable
fun ClearIcon( onClick: () -> Unit = {} ) {
    IconButton(
        onClick = onClick,
        Modifier
            .size(24.dp)
            .offset(y = 2.dp)
    ) {
        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
    }

}


@Preview
@Composable
fun CustomSearchBar_() {
    val list: List<Pair<String, @Composable () -> Unit>>  = listOf(
        "Active" to { Text(text = "Active") },
        //"Completed" to { Text(text = "Completed") } ,
        //"Completed2" to { Text(text = "Completed2") } ,
    )
    CustomSearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        text = "Search",
        tabList = list,
        
    )
}

