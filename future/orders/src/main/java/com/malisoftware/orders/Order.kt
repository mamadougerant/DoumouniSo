package com.malisoftware.orders

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.scaffold.NoScrollableContentTabs
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.container.ImageContainer
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.components.icons.SmallLeftIcon
import com.malisoftware.components.screens.EmptyCard
import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.local.local.OrderStatus
import com.malisoftware.local.mappers.toBusinessEntity
import com.malisoftware.local.reamlModel.RealmItemOrder
import com.malisoftware.local.reamlModel.RealmItems
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme
import io.realm.kotlin.types.RealmSet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Order(
    navController: NavHostController,
    orderViewModel: OrderViewModel
) {

    LaunchedEffect(key1 = orderViewModel.orderedItem){
        orderViewModel.getCompletedOrder()
    }

    val orders by orderViewModel.orderedItem.collectAsState()


    val restaurants = orders.filter { it.restaurant?.isRestaurant == true }
    val shops = orders.filter { it.restaurant?.isRestaurant == false }

    val competedRestaurantsOrder = restaurants.filter { it.status == OrderStatus.DELIVERED.value }
    val competedShopsOrder = shops.filter { it.status == OrderStatus.DELIVERED.value }


    var index by rememberSaveable { mutableIntStateOf(0) }

    var completedScreen by rememberSaveable { mutableStateOf(false) }

    val restaurantSize by remember(completedScreen,orders) {
        mutableStateOf(if (completedScreen) competedRestaurantsOrder.size else restaurants.size)
    }

    val shopsSize by remember(completedScreen,orders) {
        mutableStateOf(if (completedScreen) competedShopsOrder.size else shops.size)
    }

    val orderByBusiness by remember(index,orders,completedScreen) {
        mutableStateOf(
            if (index == 0 && completedScreen) competedRestaurantsOrder
            else if (index== 1 && completedScreen) competedShopsOrder
            else if (index == 0 && !completedScreen) restaurants
            else shops
        )
    }

    Scaffold (
        topBar = {
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(0.dp)
            ) {
                TopAppBar(
                    title = { TextWithIcon(
                        title = "Commandes"
                    ) {} },
                    actions = {
                        Card (
                            modifier = Modifier.padding(5.dp),
                            shape = CircleShape,
                            colors = CardDefaults.cardColors(Color.LightGray),
                            onClick = {
                                completedScreen = !completedScreen
                            }
                        ){
                            val text = if (completedScreen) "En cours" else "Recommander"
                            Text(
                                text = text,
                                style = AppTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(5.dp)
                                    .padding(horizontal = 5.dp),
                                color = Color.Black
                            )
                        }
                    }
                )
                NoScrollableContentTabs(
                    list = listOf(
                        "Restaurants (${restaurantSize})" to null,
                        "Shops (${shopsSize})" to null,
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
    ) {

        if (index == 0 && restaurants.isEmpty() && !completedScreen) {
            EmptyCardImpl(
                isRestaurant = true,
                navController = navController
            )
        }
        else if (index == 1 && shops.isEmpty() && !completedScreen) {
            EmptyCardImpl(
                isRestaurant = false,
                navController = navController
            )
        }
        else if (index == 1 && competedShopsOrder.isEmpty() && completedScreen) {
            EmptyCardImpl(
                isRestaurant = false,
                navController = navController,
                text = "Pas de commande terminée"
            )
        }
        else if (index == 0 && competedRestaurantsOrder.isEmpty() && completedScreen) {
            EmptyCardImpl(
                isRestaurant = true,
                navController = navController,
                text = "Pas de commande terminée",
            )
        }
        else{
            OrderContent(
                it,
                navController,
                orderByBusiness
            )
        }
        LazyColumn(
            contentPadding = it
        ) {
            items(orderByBusiness.size){
                val order = orderByBusiness[it]
                val business = order.restaurant?.toBusinessEntity()
                if (business != null) {
                    CompletedOrderContainer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        business = business,
                        items = order.items,
                        onClick = {
                            // Give the user the ability to reorder the items
                            // ask if he want to reorder the items or see the restaurant in a bottom sheet
                            navController.navigate(
                                (if (business.isRestaurant) MainFeatures.RESTAURANT_ITEM
                                   else MainFeatures.SHOP_ITEM ) + "/${business.restaurantId}"
                            )
                        }
                    )
                    if (it != orderByBusiness.size -1 )Divider()
                }
            }
        }
    }
}

@Composable
fun EmptyCardImpl(
    navController: NavHostController,
    text: String = "Pas de commande en cours",
    actionText: String = "Ajouter des articles",
    isRestaurant: Boolean
) {
    EmptyCard(
        text = text,
        actionText = actionText,
        modifier = Modifier
            .fillMaxHeight(),
        action = {
            navController.navigate(
                if (isRestaurant) MainFeatures.RESTAURANT
                else MainFeatures.SHOP
            )
        }
    )
}

@Composable
fun OrderContent(
    it: PaddingValues,
    navController: NavHostController,
    orderByBusiness: List<RealmItemOrder>,
) {
    LazyColumn(
        contentPadding = it
    ) {
        items(orderByBusiness.size){
            val order = orderByBusiness[it]
            val business = order.restaurant?.toBusinessEntity()
            if (business != null) {
                CompletedOrderContainer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    business = business,
                    items = order.items,
                    onClick = {
                        // Give the user the ability to reorder the items
                        // ask if he want to reorder the items or see the restaurant in a bottom sheet
                        navController.navigate(
                            (if (business.isRestaurant) MainFeatures.RESTAURANT_ITEM
                            else MainFeatures.SHOP_ITEM ) + "/${business.restaurantId}"
                        )
                    }
                )
                if (it != orderByBusiness.size -1 )Divider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompletedOrderContainer(
    modifier: Modifier = Modifier,
    business: BusinessEntity = BusinessEntity(),
    onReorder: (BusinessEntity,RealmSet<RealmItems>) -> Unit = {_,_->},
    items: RealmSet<RealmItems>,
    showReorder: Boolean = false,
    onClick: () -> Unit = {}
) {
    OutlinedCard (
        modifier = modifier
            .fillMaxWidth(),
        onClick = onClick
    ) {
        val total = items.sumOf { it.price * it.quantity } + business.deliveryFee
        SmallBusinessContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            imageUrl = business.imageUrl,
            title = business.title,
            subtitle = business.category,
        ) {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = formatPrice(total), style = AppTheme.typography.titleMedium)
                SmallLeftIcon(
                    text = "Pending",
                    color = Color.LightGray,
                    textColor = Color.Black
                )
            }
        }
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ){
            items (items.size){
                val item = items.elementAt(it)
                val size = if (showReorder) 80.dp else 100.dp
                ImageContainer(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .width(size)
                        .height(size),
                    imageUrl = item.imageUrl,
                    topRightIcon = {
                        SmallLeftIcon(
                            text = item.quantity.toString(),
                            modifier = Modifier.padding(5.dp),
                            color = AppTheme.colors.background,
                            textColor = AppTheme.colors.onBackground
                        )
                    },
                    onClick = {
                        //navController.navigate(MainFeatures.CART_ITEM + "/${order.id}")
                    }
                )
            }
        }
        if (showReorder)
            Button(
                onClick = { onReorder.invoke(business,items) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
                    .padding(horizontal = 5.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(Color.Black),
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Recommander",
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