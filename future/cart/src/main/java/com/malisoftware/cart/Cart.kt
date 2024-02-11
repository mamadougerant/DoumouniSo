package com.malisoftware.cart

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.DeleteActionModal
import com.malisoftware.components.component.scaffold.ContentTabs
import com.malisoftware.components.component.scaffold.NoScrollableContentTabs
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(
    navController: NavController,
    cartVm: CartRoomViewModel,
) {

    LaunchedEffect(key1 = cartVm,) {
        cartVm.getOrders()
    }
    var openSheet by remember { mutableStateOf(false) }
    var sheetContent by remember { mutableStateOf(ItemOrderEntity()) }

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
                        .padding(horizontal = 50.dp)
                        .offset(y = (-10).dp)
                )
            }
        },
    ) { paddingValues ->
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
                    business = order.restaurant,
                    onClear = {
                        sheetContent = order
                        openSheet = true
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
        if (openSheet) {
            DeleteActionModal(
                onDismissRequest = { openSheet = false },
                onConfirm = {
                    scope.launch {
                        cartVm.deleteOrder(sheetContent, sheetContent.id)
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
fun CartItemContainer(
    modifier: Modifier = Modifier,
    business: BusinessEntity = BusinessEntity(),
    onClear: () -> Unit = {},
    onBusinessForward: (BusinessEntity) -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    OutlinedCard (
        modifier = modifier
            .fillMaxWidth()
    ) {
        SmallBusinessContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            imageUrl = business.imageUrl,
            title = business.title,
            subtitle = business.category,
        ) { IconButton(onClick = onClear) {
            Icon(Icons.Rounded.Clear, contentDescription = "")
        } }
        Button(
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(horizontal = 5.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(AppTheme.colors.onBackground),
            contentPadding = PaddingValues(0.dp)
        ) {
            val text = buildAnnotatedString {
                withStyle(AppTheme.typography.titleMedium.toSpanStyle()) {
                    append("Voir le panier")
                }
            }
            Text(
                text = text,
                style = AppTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 15.dp, horizontal = 10.dp),
                color = AppTheme.colors.background
            )
        }
        Button(
            onClick = { onBusinessForward.invoke(business) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(horizontal = 5.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color.LightGray.copy(0.5f) else Color.LightGray),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Voir l'etablissement",
                style = AppTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(15.dp),
                color = AppTheme.colors.background
            )
        }


    }
}

@Preview
@Composable
fun Cart_() {
    //Cart(navController = NavController(LocalContext.current), cartVm = cartVm)


}