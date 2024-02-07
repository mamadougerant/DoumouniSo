package com.malisoftware.cart

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.LazyLists.HorizontalCartItemList
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.scaffold.CustomTopBar
import com.malisoftware.components.constants.NavConstant.MainFeatures
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
    LaunchedEffect(key1 = viewModel.items.collectAsState()) {
        viewModel.getAllOrderByRestaurantId(id)
        //viewModel.getOrders()
    }

    val orders by viewModel.items.collectAsState()
    val businesses by viewModel.orders.collectAsState()
    val business = businesses.find { it.id == id }
    val items = orders.map { it.toItems() }
    val scope = rememberCoroutineScope()

    var instruction by rememberSaveable { mutableStateOf(business?.specialInstruction) }

    val scrollState = rememberLazyListState()
    //TODO: alerter si le restaurant est fermé

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
                    Log.d("CartItems", "Checkout")
                }
            )
        }
    ){ paddingValues ->
        LazyColumn(
            state = scrollState,
            modifier = Modifier.padding(paddingValues)
        ){

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
                    Log.d("CartItems", "Item: $item")
                },
                onQuantityChange = {quantity, item ->
                    scope.launch {
                        viewModel.insertOrderItem(quantity,item,id)
                    }
                }
            )

            item {
                TextButton(
                    onClick = {
                        navController.navigate(MainFeatures.HOME)
                        navController.navigate(MainFeatures.RESTAURANT_ITEM + "/${id}")
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
                    onValueChange = { instruction = it },
                )
            }
        }
    }
}

@Composable
fun Waring() {

}

@Composable
fun CartItemBottomBar(
    modifier: Modifier = Modifier,
    total: Double,
    deliveryFee: Double = 0.0,
    enabled: Boolean = true,
    onCheckout: () -> Unit = {},
    onUserTerms: () -> Unit = {}
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp)
    ) {
        TextWithIcon(
            title = "Sous-Total", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            customStyle = AppTheme.typography.titleMedium
        ) {
            Text(text = formatPrice(total), style = AppTheme.typography.titleMedium)
        }
        TextWithIcon(
            title = "Frais de Livrason", modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp),
            customStyle = AppTheme.typography.titleMedium
        ) {
            Text(text = formatPrice(deliveryFee), style = AppTheme.typography.titleMedium)
        }

        TextWithIcon(title = "Total", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)) {
            Text(text = formatPrice(total + deliveryFee), style = AppTheme.typography.titleMedium)
        }

        val text = buildAnnotatedString {
            withStyle(AppTheme.typography.titleSmall.toSpanStyle()) {
                append("En continuant vous acceptez les ")
            }
            withStyle(AppTheme.typography.titleSmall.copy(textDecoration = TextDecoration.Underline).toSpanStyle()) {
                append("conditions de vente,")
            }
            withStyle(AppTheme.typography.titleSmall.toSpanStyle()) {
                append(" vous acceptez les ")
            }
            withStyle(AppTheme.typography.titleSmall.copy(textDecoration = TextDecoration.Underline).toSpanStyle()) {
                append("conditions d'utilisation")
            }
        }
        Text(
            text = text ,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp)
                .clickable { onUserTerms() },
            textAlign = TextAlign.Start,
        )

        Button(
            onClick = { onCheckout() },
            colors = ButtonDefaults.buttonColors(
                AppTheme.colors.onBackground,
                AppTheme.colors.background
            ),
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(vertical = 5.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Commander",
                style = AppTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center
            )
        }
    }

}