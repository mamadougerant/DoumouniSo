package com.future.cart

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.cart.CartRoomViewModel
import com.malisoftware.components.LazyLists.HorizontalItemList
import com.malisoftware.components.LazyLists.ItemList
import com.malisoftware.components.container.PlusIcon
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.components.container.icons.ArrowForward
import com.malisoftware.components.container.icons.NavigationIcon
import com.malisoftware.components.screens.OrderScreenInModalSheet
import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme

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
    var sheetContent by remember { mutableStateOf(Items()) }

    val orders by cartVm.orders.collectAsState()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Cart",
                        style = AppTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    NavigationIcon(
                        modifier = Modifier.height(30.dp),
                        onClick = { navController.navigateUp() },
                        color = Color.Unspecified
                    )
                },
                actions = {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(5.dp)
                            .size(30.dp)
                    ) {
                        Icon(Icons.Rounded.Delete, contentDescription = "")
                    }
                },

            )
        },
    ) { paddingValues ->
        LazyColumn (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            items (orders.size ){
                val order = orders[it]
                CartItemContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    business = order.restaurant,
                )

            }
        }
        if (openSheet) {
            OrderScreenInModalSheet(
                item = sheetContent,
                onSheetClose = { openSheet = it },
                bottomContent = {}
            ) {

                HorizontalItemList(
                    items = List(10){ Items() },
                    customIcon = {PlusIcon()},
                    title = "Dans votre panier",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                )
            }
        }
    }
}

@Composable
fun CartItemContainer(
    modifier: Modifier = Modifier,
    business: BusinessEntity = BusinessEntity(),
    onBusinessClick: () -> Unit = {},
    onCancel: () -> Unit = {},
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
        ) { ArrowForward(onClick = onBusinessClick) }
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
            onClick = onCancel,
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
                text = "Supprimer",
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