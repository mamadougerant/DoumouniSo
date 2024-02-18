package com.malisoftware.components.screens

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.malisoftware.components.R
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.component.scaffold.CustomTopBar
import com.malisoftware.components.container.ImageContainer
import com.malisoftware.components.icons.NavigationIcon
import com.malisoftware.model.Items
import com.malisoftware.model.ItemsList
import com.malisoftware.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun PagerImageContainer(
    listItem: List<Items> = List(3) {Items(title = "title $it")},
    onQuantityChange: (Int,Items) -> Unit = {_,_->},
    title: String = "title",
    onQuit: ()-> Unit = { }
) {

    val state = rememberPagerState(pageCount = { listItem.size })

    val scope = rememberCoroutineScope()
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = title, style = AppTheme.typography.titleLarge) },
                navigationIcon = {
                    NavigationIcon(
                        onClick = onQuit,
                        navIcon = {
                            Icon(
                                Icons.Default.Clear,
                                contentDescription = "Back",
                                tint = Color.Black
                            )
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
            )
        },
        bottomBar = {
            AllPagerButtons(
                modifier = Modifier,
                onQuantityChange = { onQuantityChange.invoke(it,listItem[state.currentPage]) },
                initQuantity = listItem[state.currentPage].quantity,
                scope = scope,
                state = state,
                listSize = listItem.size
            )
        },
        containerColor = AppTheme.colors.background.copy(alpha = .92f)
    ) { paddingValues ->

        HorizontalPager(
            state = state,
            modifier = Modifier
                .padding(top = paddingValues.calculateTopPadding())
                .fillMaxSize()
        ) {pageCount->
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(20.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    ImageContainer(
                        imageUrl = listItem[pageCount].imageUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight(0.45f)
                            .padding(horizontal = 10.dp),
                        enabled = true,
                        onClick = { },
                        shape = RoundedCornerShape(20.dp)

                    )
                    TextDisposition(
                        modifier = Modifier
                            .height(80.dp)
                            .padding(horizontal = 15.dp),
                        h1 = listItem[pageCount].title,
                        h1Style = AppTheme.typography.titleLarge,
                        h2 = listItem[pageCount].formattedPrice,
                        h2Style = AppTheme.typography.titleMedium,
                    ) {

                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AllPagerButtons(
    modifier: Modifier = Modifier,
    onQuantityChange: (Int) -> Unit = {},
    initQuantity: Int = 1,
    scope: CoroutineScope,
    state: PagerState,
    listSize: Int,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)

    ) {
        PagerButtons(
            modifier = Modifier.fillMaxWidth(),
            onQuantityChange = onQuantityChange,
            initQuantity = initQuantity
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            PagerButton(
                icon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        null,
                        tint = Color.Black
                    )
                },
                onClick = {
                    if (state.currentPage > 0)
                        scope.launch {
                            state.animateScrollToPage(state.currentPage - 1)
                        }
                }
            )
            PagerButton(
                icon = {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        null,
                        tint = Color.Black
                    )
                },
                onClick = {
                    if (state.currentPage < listSize - 1)
                        scope.launch {
                            state.animateScrollToPage(state.currentPage + 1)
                        }
                }
            )
        }
    }
}


@Composable
fun PagerButtons(
    modifier: Modifier = Modifier,
    onQuantityChange: (Int) -> Unit = {},
    initQuantity: Int = 1
) {
    var quantity by remember(initQuantity) { mutableIntStateOf(initQuantity) }
    Row (
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        PagerButton(
            icon = {
                Icon(
                    painterResource(id = R.drawable.ic_baseline_minus),
                    null,
                    tint = Color.Black
                )
            },
            onClick = { quantity-- ; onQuantityChange(quantity)}
        )
        PagerButton(
            icon = {
                Text(
                    text = quantity.toString() ,
                    style = AppTheme.typography.titleMedium,
                    color = Color.Black
                )
            },
            onClick = { }
        )

        PagerButton(
            icon = {
                Icon(
                    Icons.Default.Add,
                    null,
                    tint = Color.Black
                )

            },
            onClick = { quantity++ ; onQuantityChange(quantity) }
        )

    }
}


@Composable
fun PagerButton(
    icon: (@Composable () -> Unit)? = null,
    onClick: () -> Unit = {}
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(50)),
        colors = IconButtonDefaults.iconButtonColors(Color.LightGray)
    ) {
        icon?.invoke() ?:
        Icon(Icons.Default.Add , null )
    }
}
