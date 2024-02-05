package com.malisoftware.components.component.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ItemCustomScaffold(
    modifier: Modifier = Modifier,
    text: String = "ScrollableTopAppBar",
    imageUrl: String = "",
    scrollState: LazyListState = rememberLazyListState(),
    offset: Int = 270,
    barColor: Color = Color.White,
    navIcon: (@Composable () -> Unit)? = null,
    onNavIconClick: () -> Unit = {},
    actions: (@Composable (Color) -> Unit)? = {},
    bottomBar: (@Composable () -> Unit) = {},
    contentPaddingValues: (PaddingValues)->Unit = {  },
    content: LazyListScope.() -> Unit = {},
) {
    CoreScaffold(
        modifier = modifier,
        topBar = {
            CustomTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .offset(y = (-25).dp),
                text = text,
                scrollState = scrollState,
                offset = offset,
                barColor = barColor,
                navIcon = navIcon,
                onNavIconClick = onNavIconClick,
                showBarAtIndex = null,
                actions = actions
            )
        },
        imageUrl = imageUrl,
        scrollState = scrollState,
        contentPaddingValues = contentPaddingValues,
        content = content,
        bottomBar = bottomBar
    )
}
@Preview
@Composable
fun SimpleCustomScaffold_(){
    ItemCustomScaffold(
        scrollState = rememberLazyListState(),
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
        actions = {

        },
        navIcon = {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
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