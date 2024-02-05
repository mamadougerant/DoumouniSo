package com.future.shop

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malisoftware.components.LazyLists.ItemList
import com.malisoftware.components.component.scaffold.BusinessScreenScaffold
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.container.BusinessInfo
import com.malisoftware.components.container.ItemWithTitleBar
import com.malisoftware.components.screens.OrderScreenInModalSheet
import com.malisoftware.model.Items
import com.malisoftware.model.ItemsList
import com.malisoftware.theme.AppTheme
import com.future.shop.viewModel.ShopViewModel
import kotlinx.coroutines.launch

@Composable
fun ShopItem(id: String, navController: NavHostController, viewModel: ShopViewModel) {
    Log.d("RestaurantItem", "RestaurantItem: ${id}")
    val items = listOf(
        ItemsList(
            title = "shop1",
            items = List(5){ Items(id=it,title = "title1 $it") }
        ),
        ItemsList(
            title = "title2",
            items = List(4){ Items(id=it,title = "title2 $it") }
        ),
        ItemsList(
            title = "title3",
            items = List(4){ Items(id=it,title = "title3 $it") }
        ),
        ItemsList(
            title = "title4",
            items = List(4){ Items(id=it,title = "title4 $it") }
        ),
        ItemsList(
            title = "title5",
            items = List(4){ Items(id=it,title = "title5$it") }
        ),
        ItemsList(
            title = "title6",
            items = List(4){ Items(id=it,title = "title6 $it") }
        ),
        ItemsList(
            title = "title8",
            items = List(4){ Items(id=it,title = "title7 $it") }
        )
    )
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var sheetContent by remember { mutableStateOf(Items()) }
    var openSheet by remember { mutableStateOf(false) }
    BusinessScreenScaffold(
        scrollState = scrollState,
        modifier = Modifier,
        text = "Restaurant",
        imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/800px-Good_Food_Display_-_NCI_Visuals_Online.jpg",
        barExtraContent = {
            ContentTabs(
                list = items.map { it.title to null },
                onIndexChange = {
                    scope.launch {
                        scrollState.animateScrollToItem(it+1)
                    }
                },
                containerColor = Color.White
            )
        },
        onNavIconClick = { navController.navigateUp() },
        showBarAtIndex = 1
    ) {
        item {
            Card (
                colors = CardDefaults.cardColors(AppTheme.colors.background),
                shape = RoundedCornerShape(0.dp),
            ) {
                BusinessInfo(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                ItemWithTitleBar()
            }
        }
        items(items.size) { index ->
            val allItems = items[index].items
            val title = items[index].title
            Card (
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(AppTheme.colors.background)
            ) {
                ItemList(
                    modifier = Modifier.padding(10.dp),
                    items = allItems,
                    title = title,
                    onQuantityChange = { },
                )
            }

        }

    }
    Log.d("RestaurantItem", "RestaurantItem: ${sheetContent}")

    if (openSheet) {
        OrderScreenInModalSheet(
            item = sheetContent,
            onSheetClose = { openSheet = it },
        ) {

        }
    }
}