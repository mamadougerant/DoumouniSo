package com.malisoftware.restaurant

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malisoftware.components.LazyLists.HorizontalItemList
import com.malisoftware.components.component.scaffold.BusinessScreenScaffold
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.container.BusinessInfo
import com.malisoftware.components.container.ItemWithTitleBar
import com.malisoftware.components.screens.OrderScreenInModalSheet
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme
import com.future.restaurant.viewModel.RestaurantOrderVM
import com.future.restaurant.viewModel.RestaurantViewModel
import kotlinx.coroutines.launch


@Composable
fun RestaurantItem(
    id: String, navController: NavHostController,
    viewModel: RestaurantViewModel,
    orderViewModel: RestaurantOrderVM,
) {

    // TODO: check if the restaurant is open before ordering

    LaunchedEffect(key1 = viewModel, ){
        viewModel.getRestaurant(id)
        orderViewModel.getRestaurantItems(id)
    }

    val restaurant by viewModel.restaurantById.collectAsState()
    val restaurantItems by orderViewModel.restaurantItems.collectAsState()
    val items = restaurantItems.map { it.items }.flatten()
    val promotionItems by orderViewModel.itemsInPromotion.collectAsState()



    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var sheetContent by remember { mutableStateOf(Items()) }
    var openSheet by remember { mutableStateOf(false) }

    if (restaurant == null) { return }

    BusinessScreenScaffold(
        scrollState = scrollState,
        modifier = Modifier,
        text = restaurant?.title!!,
        imageUrl = restaurant?.imageUrl!!,
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
                        .fillMaxWidth(),
                    title = restaurant?.title!!,
                    subtitle = restaurant?.formattedMinPrice + " - " + restaurant?.promotion,
                    subInCard1 = restaurant?.formattedDeliveryTime!!,
                    subInCard2 = restaurant?.formattedDeliveryFee!!,
                )
                ItemWithTitleBar(promotionItems)
            }
        }
        items.forEachIndexed { _, it ->
            HorizontalItemList(
                items = it.items,
                title = it.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = {
                    openSheet = true
                    sheetContent = it
                },
            )
        }
    }
    Log.d("RestaurantItem", "RestaurantItem: ${sheetContent}")

    if (openSheet) {
        OrderScreenInModalSheet(
            item = sheetContent,
            onSheetClose = { openSheet = it },
        ) {

            items.forEachIndexed { _, it ->
                HorizontalItemList(
                    items = it.items,
                    title = it.title,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    onClick = {
                        openSheet = true
                        sheetContent = it
                    },
                    //color = null
                )
            }
        }
    }
}



@Preview
@Composable
fun RestaurantItem_ () {

}