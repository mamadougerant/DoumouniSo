package com.malisoftware.shop

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malisoftware.components.LazyLists.ItemList
import com.malisoftware.components.component.scaffold.BusinessScreenScaffold
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.screens.OrderScreenInModalSheet
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme
import com.future.shop.viewModel.ShopViewModel
import com.malisoftware.components.LazyLists.GridItemList
import com.malisoftware.components.container.ItemHeader
import com.malisoftware.components.icons.ArrowForward
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.shop.viewModel.ShopOrderVm
import com.malisoftware.shop.viewModel.ShopRoomVm
import kotlinx.coroutines.launch

@Composable
fun ShopItem(
    id: String,
    navController: NavHostController,
    viewModel: ShopViewModel,
    shopOrderVM: ShopOrderVm,
    shopRoomVm: ShopRoomVm
) {
    LaunchedEffect(key1 = viewModel, ){
        viewModel.getShopById(id)
        shopOrderVM.getShopItems(id)
        shopRoomVm.getAllOrderByShopId(id)
    }

    LaunchedEffect(key1 = shopRoomVm.favorite, ){
        shopRoomVm.getFavorites()
    }


    val shop by viewModel.shopById.collectAsState()
    val shopItems by shopOrderVM.shopItems.collectAsState()
    val items = shopItems.map { it.items }.flatten()
    val itemsInPromotion by shopOrderVM.itemsInPromotion.collectAsState()
    // a pair of title and items
    val shopFilterContents by shopOrderVM.shopFilterContent.collectAsState()

    val orderItem by shopRoomVm.items.collectAsState()
    val favorites by shopRoomVm.favorite.collectAsState()
    val favoritesIds = favorites.map { it.favoriteBusiness.restaurantId }

    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    var sheetContent by remember { mutableStateOf(Items()) }
    var openSheet by remember { mutableStateOf(false) }

    var instruction by remember { mutableStateOf("") }





    val searchItems by shopOrderVM.searchItems.collectAsState()

    if (shop == null) { return }

    BusinessScreenScaffold(
        scrollState = scrollState,
        modifier = Modifier,
        text = shop!!.title,
        imageUrl = shop?.imageUrl!!,
        barExtraContent = {
            ContentTabs(
                list = items.map { it.title to null },
                onIndexChange = { scope.launch { scrollState.animateScrollToItem(it+1) } },
                containerColor = Color.White
            )
        },
        searchContent = {
            val searchItem = updateQuantity(searchItems, orderItem)
            GridItemList (
                modifier = Modifier.padding(10.dp),
                items = searchItem,
                title = "Resultat",
                onQuantityChange = { item, quantity ->
                    scope.launch {
                        shopRoomVm.insertOrderItem(item = item.toItemEntity(shop!!.id), quantity)
                        shopRoomVm.insertOrder(shopData = shop!!, id = shop!!.id,)
                    }
                },
                textsColor = Color.Black,
                onClick = {
                    openSheet = true
                    sheetContent = it
                },
                isShopItems = true,
                trailingContent = {}
            )
        },
        onSearch = { shopOrderVM.searchItems(it) },
        onNavIconClick = { navController.navigateUp() },
        showBarAtIndex = 1,
        filterContents = if (shopFilterContents != null) {{
            ItemHeader(
                shop = shop,
                itemsInPromotion = listOf(),
                onClick = {
                    openSheet = true
                    sheetContent = it
                }
            )
            val item = updateQuantity((((shopFilterContents ?: ("" to listOf()))).second), orderItem)

            GridItemList (
                modifier = Modifier.padding(0.dp),
                items = item,
                title = (shopFilterContents ?: ("" to listOf())).first,
                onQuantityChange = { item, quantity ->
                    scope.launch {
                        shopRoomVm.insertOrderItem(item = item.toItemEntity(shop!!.id), quantity)
                        shopRoomVm.insertOrder(shopData = shop!!, id = shop!!.id,)
                        //Log.d("ShopItem", "OrderItem: ${item.quantity}")
                    }
                },
                onClick = {
                    openSheet = true
                    sheetContent = it
                },
                isShopItems = true,
                trailingContent = {
                    Text(
                        text = "Retourner",
                        modifier = Modifier.clickable {
                            shopOrderVM.setFilterContent(items = null)
                        },
                        textDecoration = TextDecoration.Underline
                    )
                }
            )
        } } else null,
        onHeartClick = {
            addFavorite(scope, shopRoomVm, shop!!, it)
        },
        isFavorite = shop!!.id in favoritesIds,

    ) {
        ItemHeader(
            shop = shop,
            itemsInPromotion = updateQuantity(itemsInPromotion, orderItem),
            onClick = {
                openSheet = true
                sheetContent = it
            }
        )
        items(items.size) { index ->
            val allItems = items[index].items
            val title = items[index].title

            val item = updateQuantity(allItems, orderItem)

            Card (
                shape = RoundedCornerShape(0.dp),
                colors = CardDefaults.cardColors(AppTheme.colors.background)
            ) {
                ItemList(
                    modifier = Modifier.padding(10.dp),
                    items = item,
                    title = title,
                    onClick = {
                        openSheet = true
                        sheetContent = it
                    },
                    isShopItems = true,
                    trailingContent = {
                        ArrowForward(onClick = { shopOrderVM.setFilterContent(title,it) })
                    },
                    onQuantityChange = { item,quantiy ->
                        scope.launch {
                            shopRoomVm.insertOrderItem(item = item.toItemEntity(shop!!.id), quantiy)
                            shopRoomVm.insertOrder(shopData = shop!!, id = shop!!.id,)
                            Log.d("ShopItem", "OrderItem: ${item.quantity}")
                        }
                    }
                )
            }

        }

    }

    if (openSheet) {
        OrderScreenInModalSheet(
            item = sheetContent,
            onSheetClose = { openSheet = it },
            onBottomBarClick = {
                scope.launch {
                    openSheet = false
                    shopRoomVm.insertOrderItem(item = it.toItemEntity(shop!!.id), it.quantity)
                    shopRoomVm.insertOrder(shopData = shop!!, shop!!.id )
                }
            },
            onValueChange = {
                instruction = it
            }
        ) {

        }
    }
}

fun updateQuantity (
    allItems: List<Items>,
    orderItem: List<ItemsEntity>
): List<Items> {
    return (allItems).map {
        val order = orderItem.find { order -> order.title == it.title && order.price == it.price }
        if (order != null) { it.copy(quantity = order.quantity) } else { it }
    }
}


