package com.malisoftware.cart

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.LazyLists.HorizontalCartItemList
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.CartItemBottomBar
import com.malisoftware.components.component.scaffold.CustomTopBar
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.formatLocalTime
import com.malisoftware.components.screens.SpecialInstruction
import com.malisoftware.local.mappers.toItems
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme
import kotlinx.coroutines.launch

@Composable
fun CartItems(
    id: String,
    navController: NavController,
    viewModel: CartRoomViewModel,
) {
    Log.d("CartItems", "CartItems: $id")
    // update the item object
    LaunchedEffect(key1 = viewModel.items.collectAsState()) {
        viewModel.getAllOrderByRestaurantId(id)
    }
    LaunchedEffect(key1 = viewModel.orders){
        viewModel.getOrders()
    }
    // fetch the items updeted in LaunchedEffect
    val orders by viewModel.items.collectAsState()
    // fecth the businesses and find the one with the id
    val businesses by viewModel.orders.collectAsState()
    val business = businesses.find { it.id == id }

    val items = orders.map { it.toItems() }

    val scope = rememberCoroutineScope()

    var instruction by remember(business?.specialInstruction) { mutableStateOf(business?.specialInstruction) }

    val scrollState = rememberLazyListState()
    //TODO: alerter si le restaurant est fermé
    //TODO: add instruction to the order

    val total = items.sumOf { it.price * it.quantity }
    val minPrice = business?.restaurant?.minPrice ?: 0.0


    Scaffold (
        topBar = {
            CustomTopBar(
                scrollState = scrollState,
                actions = {},
                text = business?.restaurant?.title ?:  "Your Cart",
                onNavIconClick = { navController.navigateUp() },
                offset = 100
            )
        },
        bottomBar = {
            CartItemBottomBar(
                total = items.sumOf { it.price * it.quantity },
                deliveryFee = business?.restaurant?.deliveryFee ?: 0.0,
                onCheckout = {
                    scope.launch {
                        instruction?.let {instruction ->
                            business?.copy(specialInstruction = instruction)
                                ?.let { viewModel.updateItemOrderEntity(it) }
                        }
                        navController.navigate(MainFeatures.CART_CHECKOUT + "/${id}")
                    }

                },
                enabled = total >= minPrice && business?.restaurant?.isOpen == true
                        && items.isNotEmpty()
            )
        }
    ){ paddingValues ->
        LazyColumn(
            state = scrollState,
            modifier = Modifier.padding(paddingValues)
        ){

            item {
                AnimatedVisibility (
                    total < minPrice ,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    val waring = if (items.isEmpty()) "Le panier est vide"
                    else "Plus que ${formatPrice(minPrice.minus(total))} " +
                            "pour atteindre le minimum de commande de ${business?.restaurant?.title}"
                    Waring(
                        text = waring,
                        title = "Ajouter des plats pour atteindre le minimum de commande"
                    )
                }
                Log.d("CartItems", "Business: ${business}")
                AnimatedVisibility (
                    business?.restaurant?.isOpen == false,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    Waring(
                        text = "Ce restaurant ouvre à ${business?.restaurant?.openingTime?.let {
                            formatLocalTime(it)
                        }} " + "et ferme à ${business?.restaurant?.closingTime?.let {
                            formatLocalTime(it)
                        }}" ,
                        title = "Ce restaurant est fermé"
                    )
                }
            }

            item {
                TextWithIcon(
                    title = business?.restaurant?.title ?:  "Your Cart",
                    icon = {},
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(horizontal = 10.dp)
                )
            }
            HorizontalCartItemList(
                modifier = Modifier.padding(horizontal = 10.dp),
                items = items,
                onClick = { item ->
                    //TODO: show modal item details
                    Log.d("CartItems", "Item: $item")
                },
                onQuantityChange = {quantity, item ->
                    scope.launch {
                        // Update the item in the database id is the restaurant id
                        viewModel.insertOrderItem(quantity,item,id)
                    }
                }
            )

            item {
                TextButton(
                    onClick = {
                        //navController.navigate(MainFeatures.HOME)
                        navController.navigate(
                            route =( if (business?.restaurant?.isRestaurant!!) MainFeatures.RESTAURANT_ITEM
                            else MainFeatures.SHOP_ITEM )+ "/${id}")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = AppTheme.colors.onBackground
                    )
                ) {
                    Text(
                        text = "Ajouter des plats",
                        style = AppTheme.typography.titleMedium,
                        textDecoration = TextDecoration.Underline,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            item {
                SpecialInstruction(
                    initialValue = instruction ?: "",
                    onValueChange = { instruction = it },
                )
            }
        }
    }
}

@Composable
fun Waring(text: String = "",title: String = "" ) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = "warning",
            tint = Color.Red,
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp)
        )
        Column (
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = title,
                style = AppTheme.typography.titleMedium.copy(color = Color.Red),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Text(
                text = text,
                style = AppTheme.typography.titleSmall,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }
    }
}

