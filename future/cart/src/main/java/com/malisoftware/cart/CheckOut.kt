package com.malisoftware.cart

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.OrderCompletedScreen
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.container.AddressIcon
import com.malisoftware.components.container.AddressModal
import com.malisoftware.components.container.RadioColumn
import com.malisoftware.components.icons.NavigationIcon
import com.malisoftware.local.mappers.toItems
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOut(
    modifier: Modifier = Modifier,
    navController: NavController,
    restaurantId: String = "123456789",
    cartVm: CartRoomViewModel,
) {
    // TODO: set up adress

    // update the item object getAllOrderByRestaurantId
    LaunchedEffect(key1 = cartVm.items.collectAsState(),) {
        cartVm.getAllOrderByRestaurantId(restaurantId)
    }
    LaunchedEffect(key1 = cartVm.orders){
        cartVm.getOrders()
    }
    // fetch the items updeted in LaunchedEffect
    val orders by cartVm.items.collectAsState()
    val businesses by cartVm.orders.collectAsState()
    val business = businesses.find { it.id == restaurantId }
    val items = orders.map { it.toItems() }
    
    var openSheet by remember { mutableStateOf(false) }
    var showOrderCompletedScreen by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    TextDisposition(
                        h1 =  "Check Out",
                        h1Style = AppTheme.typography.titleLarge,
                        h2 = business?.restaurant?.title ?: "Restaurant name",
                        h2Style = AppTheme.typography.titleSmall,
                    ) {}
                },
                navigationIcon = {
                    NavigationIcon(
                        modifier = Modifier.height(30.dp),
                        onClick = { navController.navigateUp() },
                        color = Color.Unspecified,
                    )
                },
            )
        },
        bottomBar = {
            Button(
                onClick = {
                    showOrderCompletedScreen = true
                    scope.launch {
                        if (business != null) {
                            cartVm.insertCompletedOrder(business, orders)
                            cartVm.deleteOrder(business, restaurantId)
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Black,Color.White)
            ) {
                Text(text = "Terminer", style = AppTheme.typography.titleLarge)
            }
        }
    ){
        LazyColumn(
            modifier = Modifier
                .padding(it)
        ){
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(horizontal = 10.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(Color.LightGray),
                    onClick = { openSheet = true }
                ) {
                }
                AddressIcon(addressLine1 = "Address1", addressLine2 = "Address2")

            }
            item {
                RadioColumn(
                    titleContent = {
                        Text(text = "Payment", style = AppTheme.typography.titleLarge)
                    },
                )
                Divider(thickness = 5.dp)

            }

            item {
                Text(
                    text = "Sammary",
                    style = AppTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                )

            }

            items (items.size){
                val item = items[it]
                val text = buildAnnotatedString {
                    withStyle(AppTheme.typography.titleSmall.toSpanStyle()) {
                        append(item.quantity.toString() + "x ")
                    }
                    withStyle(AppTheme.typography.titleSmall.toSpanStyle()) {
                        append(item.title)
                    }
                }
                TextWithIcon(
                    title = text.toString(),
                    customStyle = AppTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(text = formatPrice(item.price * item.quantity), style = AppTheme.typography.titleSmall)
                }

            }
            item {
                val deliveryFee = business?.restaurant?.deliveryFee ?: 0.0
                val total = items.sumOf { it.price * it.quantity } + deliveryFee
                TextWithIcon(
                    title = "Livraison",
                    customStyle = AppTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(text = formatPrice(deliveryFee), style = AppTheme.typography.titleSmall)
                }
                Spacer(Modifier.height(5.dp))
                TextWithIcon(
                    title = "Total",
                    customStyle = AppTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                ) {
                    Text(text = formatPrice(total), style = AppTheme.typography.titleMedium)
                }
            }
        }

        if (openSheet) {
            AddressModal(
                onStaClose = { openSheet = it }
            )
        }
        if (showOrderCompletedScreen) {
            OrderCompletedScreen(
                onFinished = {
                    //TODO : navigate to commandes
                    showOrderCompletedScreen = !it
                    navController.navigateUp()
                }
            )
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CheckOut_() {
    val sheetState = rememberModalBottomSheetState()
   ModalBottomSheet(
       onDismissRequest = { /*TODO*/ }
   ) {
       
   }
}