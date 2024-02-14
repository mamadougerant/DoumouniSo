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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malisoftware.components.LazyLists.HorizontalItemList
import com.malisoftware.components.component.scaffold.BusinessScreenScaffold
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.container.BusinessInfo
import com.malisoftware.components.container.ItemHeader
import com.malisoftware.components.container.ItemWithTitleBar
import com.malisoftware.components.screens.OrderScreenInModalSheet
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme
import com.malisoftware.restaurant.viewModel.RestaurantOrderVM
import com.malisoftware.restaurant.viewModel.RestaurantViewModel
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.restaurant.viewModel.RoomViewModel
import kotlinx.coroutines.launch


@Composable
fun RestaurantItem(
    id: String, navController: NavHostController,
    viewModel: RestaurantViewModel,
    orderViewModel: RestaurantOrderVM,
    roomVm: RoomViewModel,
) {

    LaunchedEffect(key1 = viewModel, ){
        viewModel.getRestaurant(id)
        orderViewModel.getRestaurantItems(id)
        roomVm.getAllOrderByRestaurantId(id)
    }
    LaunchedEffect(key1 = roomVm.favorite, ){
        roomVm.getFavorites()
    }

    val uiState by orderViewModel.state.collectAsState()
    val vmState by viewModel.vmState.collectAsState()

    //val restaurant by viewModel.restaurantById.collectAsState()


    val orderItem by roomVm.items.collectAsState()
    val favorites by roomVm.favorite.collectAsState()

    val favoritesIds = favorites.map { it.favoriteBusiness.restaurantId }


    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var sheetContent by remember { mutableStateOf(Items()) }
    var openSheet by remember { mutableStateOf(false) }

    var instruction by remember { mutableStateOf("") }

    if (vmState.restaurantById == null) { return }

    BusinessScreenScaffold(
        scrollState = scrollState,
        modifier = Modifier,
        text = vmState.restaurantById?.title!!,
        imageUrl = vmState.restaurantById?.imageUrl!!,
        barExtraContent = {
            if (uiState.restaurantItems.size > 2)
                ContentTabs(
                    list = uiState.restaurantItems.map { it.title to null },
                    onIndexChange = {
                        scope.launch {
                            scrollState.animateScrollToItem(it+1)
                        }
                    },
                    containerColor = Color.White
                )
        },
        searchContent = {
            val item = uiState.searchResults.updateQuantity(orderItem)
            HorizontalItemList(
                items = item,
                title = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                onClick = {
                    openSheet = true
                    sheetContent = it
                },
                color = Color.Unspecified,
                textsColor = Color.Black,
            )
        },
        onSearch = { orderViewModel.searchItems(it) },
        onNavIconClick = { navController.navigateUp() },
        showBarAtIndex = 1,
        onHeartClick = {
            addFavorite(scope, roomVm, vmState.restaurantById!!, it)
        },
        isFavorite = vmState.restaurantById!!.id in favoritesIds,
    ) {
        ItemHeader(
            shop = vmState.restaurantById,
            itemsInPromotion = uiState.itemsInPromotion,
            onClick = {
                openSheet = true
                sheetContent = it
            }
        )
        uiState.restaurantItems.forEachIndexed { _, it ->
            val item = (it.items).updateQuantity(orderItem)
            HorizontalItemList(
                items = item,
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
            onBottomBarClick = {
                scope.launch {
                    openSheet = false
                    roomVm.insertOrderItem(item = it.toItemEntity(vmState.restaurantById!!.id))
                    roomVm.insertOrder(restaurant = vmState.restaurantById!!, vmState.restaurantById!!.id )
                }
            },
            onValueChange = {
                instruction = it
            }
        ) {


        }
    }
}

fun List<Items>.updateQuantity (orderItem:  List<ItemsEntity>): List<Items> {
    return (this).map {
        val order = orderItem.find { order -> order.title == it.title && order.price == it.price }
        if (order != null) { it.copy(quantity = order.quantity) } else { it }
    }
}


@Preview
@Composable
fun RestaurantItem_ () {

}