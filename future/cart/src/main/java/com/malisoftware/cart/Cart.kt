package com.malisoftware.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.DeleteActionModal
import com.malisoftware.components.component.scaffold.NoScrollableContentTabs
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.container.CartItemContainer
import com.malisoftware.components.screens.EmptyCard
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.mappers.toBusinessData
import com.malisoftware.local.mappers.toBusinessEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(
    navController: NavController,
    cartVm: CartRoomViewModel,
) {
    // TODO add the ability to move from restaurant to shop and vice versa
    // TODO prevent the paysage mode
    LaunchedEffect(key1 = cartVm,) {
        cartVm.getOrders()
    }
    var openSheet by remember { mutableStateOf(false) }
    var sheetContent by remember { mutableStateOf(ItemOrderEntity()) }
    val sheetItems by cartVm.items.collectAsState()

    val orders by cartVm.orders.collectAsState()

    val restaurants = orders.filter { it.restaurant.isRestaurant }
    val shops = orders.filter { !it.restaurant.isRestaurant }

    var index by rememberSaveable { mutableIntStateOf(0) }

    var orderByBusiness by remember(index,orders) { mutableStateOf(if (index == 0) restaurants else shops) }

    val scope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                TopAppBar(
                    title = { TextWithIcon(
                        title = "Cart"
                    ) {} },
                )
                NoScrollableContentTabs(
                    list = listOf(
                        "Restaurants (${restaurants.size})" to null,
                        "Shops (${shops.size})" to null,
                    ),
                    onIndexChange = {
                        index = it
                    },
                    indexInitial = index,
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .offset(y = (-10).dp)
                )
            }
        },
    ) { paddingValues ->
        if (index == 0 && restaurants.isEmpty()) {
            EmptyCard(
                modifier = Modifier
                    .fillMaxHeight(),
                action = {
                    navController.navigate(MainFeatures.RESTAURANT)
                }
            )
        }
        else if (index == 1 && shops.isEmpty()) {
            EmptyCard(
                modifier = Modifier
                    .fillMaxHeight(),
                action = {
                    navController.navigate(MainFeatures.SHOP)
                }

            )
        }
        else{
            CartContent(
                paddingValues = paddingValues,
                navController = navController,
                orderByBusiness = orderByBusiness,
                onClear = {
                    sheetContent = it
                    scope.launch { cartVm.getAllOrderByRestaurantId(it.id) }
                    openSheet = true
                }
            )
        }

        if (openSheet) {
            DeleteActionModal(
                onDismissRequest = { openSheet = false },
                onConfirm = {
                    scope.launch {
                        //TODO: items are not deleted
                        cartVm.deleteOrder(sheetContent, sheetContent.id)
                        cartVm.deleteOrderAllItem(sheetItems)
                        openSheet = false
                    }
                },
                text = "Voulez-vous vraiment supprimer ce panier?",
                onCancel = { openSheet = false }
            )
        }
    }
}

@Composable
fun CartContent(
    paddingValues: PaddingValues,
    navController: NavController,
    orderByBusiness: List<ItemOrderEntity>,
    onClear: (ItemOrderEntity) -> Unit,
) {
    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        items (orderByBusiness.size ){ orderPosition ->

            val order = orderByBusiness[orderPosition]
            CartItemContainer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                business = order.restaurant.toBusinessData(),
                onClear = {
                    onClear(order)
                },
                onConfirm = {
                    navController.navigate(MainFeatures.CART_ITEM + "/${order.id}")
                },
                onBusinessForward = {
                    //navController.navigate(MainFeatures.HOME)
                    navController.navigate(
                        route = (if (it.isRestaurant)MainFeatures.RESTAURANT_ITEM
                        else MainFeatures.SHOP_ITEM) +  "/${order.id}"
                    )
                }
            )

        }
    }
}





@Preview
@Composable
fun Cart_() {
    //Cart(navController = NavController(LocalContext.current), cartVm = cartVm)


}