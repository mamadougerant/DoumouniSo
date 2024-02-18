package com.malisoftware.components.component.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CoreScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    imageUrl: String = "",
    scrollState: LazyListState = rememberLazyListState(),
    containerColor: Color = Color.Unspecified,
    contentPaddingValues: (PaddingValues)->Unit = {  },
    content: LazyListScope.() -> Unit = {},
) {
    Scaffold(
        modifier = Modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        containerColor = containerColor
    ) {
        contentPaddingValues.invoke(it)
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp)
            )
            LazyColumn(
                state = scrollState,
                modifier = modifier

            ) {
                item {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(170.dp)

                    )
                }

                content()
            }
        }
    }
}