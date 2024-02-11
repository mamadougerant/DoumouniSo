package com.malisoftware.shop

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.future.shop.ShopShimmer
import com.malisoftware.components.LazyLists.ChipList
import com.malisoftware.components.LazyLists.ColumnBusinessList
import com.malisoftware.components.LazyLists.RoundedCategoryList
import com.malisoftware.components.LazyLists.RowBusinessList
import com.malisoftware.components.LazyLists.SmallBusinessList
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.RangeSliderWithData
import com.malisoftware.components.component.RangeSliderWithGraph
import com.malisoftware.components.component.scaffold.HomeScaffoldWithBar
import com.malisoftware.components.constants.FilterConstant
import com.malisoftware.components.icons.ArrowForward
import com.malisoftware.components.icons.NavigationIcon
import com.malisoftware.components.formatPrice
import com.malisoftware.model.BusinessData
import com.malisoftware.model.CategoryData
import com.malisoftware.theme.AppTheme
import com.future.shop.viewModel.ShopViewModel
import com.malisoftware.components.container.ContinueOrder
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.mappers.toBusinessData
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.shop.viewModel.ShopOrderVm
import com.malisoftware.shop.viewModel.ShopRoomVm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ShopScreen(
    navController: NavHostController,
    viewModel: ShopViewModel,
    shopOrderVM: ShopOrderVm,
    shopRoomVm: ShopRoomVm
) {
    LaunchedEffect(key1 = viewModel, ){
        viewModel.getShopList()
        viewModel.getShopCategoryList()
        viewModel.getSponsors()
        viewModel.getSponsoredShop()
        viewModel.getShopInPromotion()
    }

    LaunchedEffect(key1 = shopRoomVm.favorite, ){
        shopRoomVm.getFavorites()
    }

    LaunchedEffect(key1 = shopRoomVm.orders, ){
        shopRoomVm.getOrders()
    }

    val orders by shopRoomVm.orders.collectAsState()

    val sponsorShopList by viewModel.sponsorShopList.collectAsState()
    val shopList by viewModel.shopList.collectAsState()
    val shopCategoryList by viewModel.shopCategoryList.collectAsState()
    val sponsors by viewModel.sponsors.collectAsState()
    val shopInPromotion by viewModel.shopInPromotions.collectAsState()

    val favorites by shopRoomVm.favorite.collectAsState()

    val loading by viewModel.loading.collectAsState(true)
    val scope = rememberCoroutineScope()




    val orderList = orders.filter { !it.restaurant.isRestaurant }.shuffled().take(1)



    val filteredShopList by viewModel.shopByCategory.collectAsState()

    HomeScaffoldWithBar (
        modifier = Modifier.padding(horizontal = 10.dp),
        text = "Rechercher ",
        searchTabList = listOf(
            "Market" to { },
        ),
        onQueryChange = { },
        navIcon = {
            TextWithIcon(
                title = "Market",
                modifier = Modifier,
                leadingIcon = {
                    NavigationIcon(
                        modifier = Modifier.height(30.dp),
                        onClick = { navController.navigateUp() },
                        color = Color.Unspecified
                    )
                }
            ) {}
        },
        filterContents = if ((filteredShopList != null) && !loading) {{
            categoryAndChips(
                categories = shopCategoryList,
                viewModel = viewModel,
                scope = scope,
                onClick = { }
            )
            ColumnBusinessList(
                modifier = Modifier,
                businessData = filteredShopList ?: emptyList(),
                title = {
                    ColumnBusinessListFilterTitle(
                        filteredRestaurantList = filteredShopList,
                        viewModel = viewModel,
                        scope = scope,
                    )
                },
                onClick = { navController.navigate(MainFeatures.SHOP_ITEM + "/${it.id}") },
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope, shopRoomVm, businessData, b) }
            )
            if ((filteredShopList ?: listOf() )!!.isEmpty()) {
                item {
                    Text(
                        text = "No result found",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
        } } else null,
        shimmerContent = if (loading) {{ ShopShimmer(Modifier.padding(it)) }} else null
    ) {
        categoryAndChips(
            categories = shopCategoryList,
            viewModel = viewModel,
            scope = scope,
            onClick = { }
        )

        item {
            RowBusinessListWithShopNav(
                navController = navController,
                modifier = Modifier,
                businessData = listOf(
                    BusinessData(
                        id  = "10",
                    ),
                    BusinessData(
                        id  = "11",
                    ),
                    BusinessData(
                        id  = "12",
                    ),
                    BusinessData(
                        id  = "13",
                    ),
                ),
                title = "Recently Viewed",
                trailingContent = {
                    ArrowForward(onClick = { })
                },

            )
        }
        item {
            if (orderList.isNotEmpty()){
                val order = orderList[0]
                ContinueOrder(
                    imageUrl = order.restaurant.imageUrl,
                    title = order.restaurant.title,
                    subtitle = order.restaurant.category,
                    arrowForward = { navController.navigate(MainFeatures.CART_ITEM + "/${order.id}") },
                )
            }
        }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = sponsorShopList,
            title = { Divider() },
            onClick = { navController.navigate(MainFeatures.SHOP_ITEM + "/${it.id}") },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b -> addFavorite(scope, shopRoomVm, businessData, b) }
        )
        item {
            if (shopInPromotion.isNotEmpty()) {
                val items by shopOrderVM.shopItems.collectAsState()
                val shopInPromotionRandom = shopInPromotion.random()
                LaunchedEffect(key1 = shopOrderVM.shopsItems){
                    shopOrderVM.getShopItems(shopInPromotionRandom.id)
                    shopRoomVm.getAllOrderByShopId(shopInPromotionRandom.id)

                }
                val orderItem by shopRoomVm.items.collectAsState()
                val shopItems = items.map { it.items }.flatten().map { it.items }.flatten().take(8)

                val item = (shopItems).map {
                    val order = orderItem.find { order -> order.title == it.title && order.price == it.price }
                    Log.d("ShopScreen", "ShopScreen: $orderItem")
                    if (order != null) { it.copy(quantity = order.quantity) } else { it }
                }
                SmallBusinessList(
                    modifier = Modifier,
                    title = shopInPromotionRandom.title,
                    imageUrl = shopInPromotionRandom.imageUrl,
                    subtitle = shopInPromotionRandom.description,
                    onClick = { },
                    items = item,
                    onForward = { navController.navigate(MainFeatures.SHOP_ITEM + "/${shopInPromotion[0].id}") },
                    onQuantityChange = {
                        scope.launch {
                            shopRoomVm.insertOrderItem(item = it.toItemEntity(shopInPromotionRandom.id))
                            shopRoomVm.insertOrder(shopData = shopInPromotionRandom, id = shopInPromotionRandom.id,)
                        }
                    },
                )
            }
        }


        item {
            val favorite = favorites.map { it.favoriteBusiness }.filter { !it.isRestaurant }.map { it.toBusinessData() }
            RowBusinessListWithShopNav(
                modifier = Modifier,
                businessData = favorite,
                title = "Favorite",
                trailingContent = {
                    ArrowForward(onClick = { viewModel.setShopByCategory(favorite) })
                },
                navController = navController,
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope, shopRoomVm, businessData, b) }

            )
        }
        item {
            RowBusinessListWithShopNav(
                modifier = Modifier,
                businessData = shopInPromotion,
                title = "Promotion",
                trailingContent = {
                    ArrowForward(onClick = { viewModel.setShopByCategory(shopInPromotion) })
                },
                navController = navController,
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope, shopRoomVm, businessData, b) }
            )
        }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = shopList,
            title = { TextWithIcon(title = "Tous les Market", modifier = Modifier.fillMaxWidth() ){
                ArrowForward()
            } },
            onClick = { navController.navigate(MainFeatures.SHOP_ITEM + "/${it.id}") },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b -> addFavorite(scope, shopRoomVm, businessData, b) }
        )

    }
}


@Composable
fun RowBusinessListWithShopNav(
    modifier: Modifier = Modifier,
    businessData: List<BusinessData>,
    title: String,
    trailingContent: @Composable () -> Unit,
    color: Color? = null,
    favoriteBusiness: List<BusinessData> = emptyList(),
    onFavoriteClick: (BusinessData, Boolean) -> Unit = { _,_ -> },
    navController: NavHostController,
) {
    if (businessData.isEmpty()) return
    RowBusinessList(
        modifier = modifier,
        title = title,
        businessData = businessData,
        onClick = { navController.navigate(MainFeatures.SHOP_ITEM+"/${it.id}") },
        color = color,
        trailingContent = trailingContent,
        favoriteBusiness = favoriteBusiness,
        onFavoriteClick = onFavoriteClick,
    )
}

@Composable
fun ColumnBusinessListFilterTitle(
    filteredRestaurantList: List<BusinessData>?,
    viewModel: ShopViewModel,
    scope: CoroutineScope,
) {

    TextWithIcon(
        title = "result" + "(${filteredRestaurantList?.size})",
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "Annuler",
            modifier = Modifier.clickable {
                scope.launch {
                    viewModel.getShopListByCategory("")
                    viewModel.clearFilter()
                }

            },
            textDecoration = TextDecoration.Underline
        )
    }
}

fun addFavorite (
    scope: CoroutineScope,
    roomVm: ShopRoomVm,
    businessData: BusinessData,
    b: Boolean
){
    if (b) {
        scope.launch {
            roomVm.insertFavorite(businessData)
        }
    } else {
        scope.launch {
            roomVm.deleteFavorite(businessData)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.categoryAndChips(
    categories: List<CategoryData>,
    viewModel: ShopViewModel,
    scope: CoroutineScope,
    onClick: () -> Unit = {},
){

    item {
        RoundedCategoryList(
            title = null,
            modifier = Modifier.padding(top = 10.dp),
            categories = categories,
            onClick = {
                scope.launch { viewModel.getShopListByCategory(it.title) }
                onClick()
            }
        )
    }
    item {
        val chipList by viewModel.filterList.collectAsState()
        val openSheet by viewModel.openSheet.collectAsState()
        @Composable fun icon ()= Icon(
            imageVector = Icons.Filled.KeyboardArrowDown,
            contentDescription = "",
            modifier = Modifier
                .width(20.dp)
                .padding(horizontal = 0.dp)
        )
        ChipList(
            selected = chipList,
            modifier = Modifier,
            chips = listOf(
                FilterConstant.PRICE.title to { icon() },
                FilterConstant.DELIVERY_FEE.title to { icon() },
                FilterConstant.DELIVERY_TIME.title to { icon() },
                FilterConstant.RATING.title to { icon() },
                FilterConstant.OPEN_NOW.title to {},
                FilterConstant.PROMOTIONS.title to {},
            ),
            onClick = {
                viewModel.addFilter(it)
            }


        )
        @Composable
        fun PriceSlider(
            steps: Int = 10,
            onClick: (Float,Float) -> Unit = { _, _ -> }
        ) {
            RangeSliderWithGraph(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 30.dp),
                startValue = 100f,
                endValue = 10000f,
                size = 42,
                steps = steps,
                onValueChangeFinished = { },
                content = { start, end ->
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Min: ${formatPrice(start.toDouble())}")
                            Text(text = "Max: ${formatPrice(end.toDouble())}")
                        }
                        Button(
                            onClick = { onClick(start, end) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                AppTheme.colors.onBackground
                            )
                        ){
                            Text(
                                text = "Appliquer" ,
                                modifier = Modifier.padding(10.dp),
                                textAlign = TextAlign.Center,
                                color = AppTheme.colors.background,
                                style = AppTheme.typography.titleLarge
                            )
                        }
                    }
                }
            )
        }

        @Composable
        fun PriceSliderWithText(
            data: List<String> = listOf("1","2","3","4",),
            onClick: (Float,Float) -> Unit = { _, _ -> }
        ) {
            RangeSliderWithData(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp)
                    .padding(bottom = 30.dp),
                data = data,
                dataModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp),
                content = { start, end ->
                    Button(
                        onClick = { onClick(start, end) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            AppTheme.colors.onBackground
                        )
                    ){
                        Text(
                            text = "Appliquer" ,
                            modifier = Modifier.padding(10.dp),
                            textAlign = TextAlign.Center,
                            color = AppTheme.colors.background,
                            style = AppTheme.typography.titleLarge
                        )
                    }
                }

            )
        }

        if (openSheet && chipList.isNotEmpty()) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.closeSheet() },
                dragHandle = {
                    TextWithIcon(
                        title = chipList.last(),
                        modifier = Modifier
                            .padding(10.dp)
                            .padding(top = 20.dp),
                    ) {} },

                ) {
                when (chipList.last()){
                    FilterConstant.PRICE.title -> { PriceSlider(
                        onClick = { start, end ->
                            viewModel.filterPrice(start.toDouble(), end.toDouble())
                            viewModel.closeSheet()
                            Log.d("Restaurant", "PriceSlider: $start, $end")
                        }

                    ) }
                    FilterConstant.DELIVERY_FEE.title -> {PriceSlider(
                        onClick = { start, end ->
                            viewModel.filterDeliveryFee(start.toDouble(), end.toDouble())
                            viewModel.closeSheet()
                            Log.d("Restaurant", "PriceSlider: $start, $end")
                        }

                    )}
                    FilterConstant.DELIVERY_TIME.title -> {
                        PriceSliderWithText(
                            data = listOf("10","20","30","40","50"),
                            onClick = { start, end ->
                                viewModel.filterDeliveryTime(start.toInt(), end.toInt())
                                viewModel.closeSheet()
                                Log.d("Restaurant", "PriceSlider: $start, $end")
                            }
                        )
                    }
                    FilterConstant.RATING.title -> {
                        PriceSliderWithText(
                            data = listOf("0","1","2","3","4","5"),
                            onClick = { start, end ->
                                viewModel.filterRating(start.toDouble()*0.05, end.toDouble()*0.05)
                                viewModel.closeSheet()
                                Log.d("Restaurant", "RATING: $start, $end")
                            }
                        )
                    }
                    else -> { viewModel.closeSheet() }
                }
            }
        }
    }
}
