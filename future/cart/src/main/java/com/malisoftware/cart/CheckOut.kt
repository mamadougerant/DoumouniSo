package com.future.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.OrderCompletedScreen
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.container.Address
import com.malisoftware.components.container.AddressIcon
import com.malisoftware.components.container.AddressModal
import com.malisoftware.components.container.RadioColumn
import com.malisoftware.components.container.icons.NavigationIcon
import com.malisoftware.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckOut(
    modifier: Modifier = Modifier,
    navController: NavController,
    orderId: String = "123456789",
) {
    var openSheet by remember { mutableStateOf(false) }
    var showOrderCompletedScreen by remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    TextDisposition(
                        h1 = "Check Out",
                        h1Style = AppTheme.typography.titleLarge,
                        h2 = "Restaurant name",
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
                actions = {
                },

            )
        },
        bottomBar = {
            Button(
                onClick = {
                    showOrderCompletedScreen = true
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                    .height(50.dp)
                ,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                    contentColor = Color.White
                )
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
                    leftContent = {
                        Text(text = "cash on delivery", style = AppTheme.typography.titleMedium)
                    },
                    titleContent = {
                        Text(text = "Payment", style = AppTheme.typography.titleLarge)
                    },
                )
                Divider(thickness = 5.dp)

            }
            item {
                // montrer le total de la commande
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
                    showOrderCompletedScreen = !it
                    navController.navigateUp()
                }
            )
        }
    }
}



@Preview
@Composable
fun CheckOut_() {
    CheckOut(navController = NavController(LocalContext.current))

}