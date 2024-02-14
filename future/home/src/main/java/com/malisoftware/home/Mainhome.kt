package com.malisoftware.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.malisoftware.components.LazyLists.CategoryList
import com.malisoftware.components.LazyLists.ColumnBusinessList
import com.malisoftware.components.LazyLists.RoundedCategoryList
import com.malisoftware.components.LazyLists.RowBusinessList
import com.malisoftware.components.LazyLists.SmallBusinessList
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.scaffold.HomeScaffoldWithBar
import com.malisoftware.components.container.ImageContainer
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.components.container.StoreContainer
import com.malisoftware.components.icons.ArrowForward
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items
import com.doumounidron.theme.DoumouniDronTheme
import com.future.home.HomeShimmer
import com.malisoftware.home.viewModel.HomeViewModel
import com.malisoftware.components.container.ContinueOrder
import com.malisoftware.home.viewModel.HomeRoomViewModel
import com.malisoftware.local.mappers.toBusinessData
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.search.SearchContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainHome(
    navController: NavController,
    homeViewModel: HomeViewModel,
    homeRoomViewModel: HomeRoomViewModel,
) {
    // TODO add empty card pic and empty command pic
    LaunchedEffect(key1 = homeViewModel){
        homeViewModel.fetchAllData()
        homeRoomViewModel.getRecentlyViewed()
    }

    LaunchedEffect(key1 = homeRoomViewModel.favorites,){
        homeRoomViewModel.getFavorites()
    }
    LaunchedEffect(key1 = homeRoomViewModel.orders,){
        homeRoomViewModel.getOrders()
    }

    val sponsors by homeViewModel.sponsors.collectAsState()
    val stores by homeViewModel.storeList.collectAsState()
    val restaurantCategories by homeViewModel.restaurantCategoryList.collectAsState()
    val shopCategories by homeViewModel.shopCategoryList.collectAsState()
    val restaurantList by homeViewModel.restaurantList.collectAsState()
    val shopList by homeViewModel.shopList.collectAsState()
    val favorites by homeRoomViewModel.favorites.collectAsState()

    val orders by homeRoomViewModel.orders.collectAsState()
    val orderList = orders.filter { it.restaurant.isOpen }.shuffled().take(1)

    val sponsoredRestaurants by homeViewModel.sponsorRestaurantList.collectAsState()
    val sponsoredShops by homeViewModel.sponsorShopList.collectAsState()

    val recentlyViewed by homeRoomViewModel.recentlyViewed.collectAsState()

    val loading by homeViewModel.loading.collectAsState(true)

    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }

    HomeScaffoldWithBar (
        modifier = Modifier.padding(horizontal = 10.dp),
        text = "Rechercher ",
        tabTextColor = Color.Black,
        searchTabList = listOf(
            "Restaurant" to { SearchContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                onSearchResultClick = {searchQuery = it },
                query = searchQuery
            ) { navigateToItem(navController, it) }
            },
            "Market" to { SearchContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                isRestaurant = false,
                onSearchResultClick = {searchQuery = it },
                query = searchQuery
            ) { navigateToItem(navController, it) }
            },

        ),
        initialQuery = searchQuery,
        onSearch = { searchQuery = it  },
        onQueryChange = { searchQuery = it },
        navIcon = {
            /*
            Column (
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalAlignment = Alignment.Start,
            ){
                Text(
                    text = "Home",
                    style = AppTheme.typography.titleSmall,
                    modifier = Modifier.height(18.dp),
                )
                Text(
                    text = "Home",
                    style = AppTheme.typography.titleMedium,
                    modifier = Modifier.height(19.dp),
                    lineHeight = 0.sp
                )
            }*/
        },
        shimmerContent = if (loading) { { HomeShimmer(Modifier.padding(it)) } } else null,
    ){
        item {
            val storeRestaurant = stores.filter { it.name == "Restaurant" }
            val storeMarket = stores.filter { it.name == "Market" }
            StoreContainer(
                modifier = Modifier,
                imageUrl1 = if (storeRestaurant.isNotEmpty()) storeRestaurant[0].imageUrl else "",
                imageUrl2 = if (storeMarket.isNotEmpty()) storeMarket[0].imageUrl else "",
                text1 = if (storeRestaurant.isNotEmpty()) storeRestaurant[0].name else "",
                text2 = if (storeMarket.isNotEmpty()) storeMarket[0].name else "",
                onImage1Click = { navController.navigate(Roots.RESTAURANT_ROOT) },
                onImage2Click = { navController.navigate(Roots.SHOP_ROOT) },
            )
        }
        // RESTARANT  category
        item {
            CategoryList (
                modifier = Modifier,
                categories = restaurantCategories,
                trailingContent = {},
                onClick = {
                    //TODO : handle the click an other way
                    navController.navigate(MainFeatures.RESTAURANT)
                }
            )
        }

        // orders in the cart
        item {
            if (orders.isNotEmpty()){
                val order = orderList[0]
                ContinueOrder(
                    imageUrl = order.restaurant.imageUrl,
                    title = order.restaurant.title,
                    subtitle = order.restaurant.category,
                    arrowForward = {
                        navController.navigate(MainFeatures.CART_ITEM + "/${order.id}")
                    }
                )
            }

        }

        // mix of the restaurants and shops
        //navigate to the restaurant and shop on click
        item {
            RowBusinessListWithNav(
                navController = navController,
                modifier = Modifier,
                businessData = recentlyViewed.map { it.toBusinessData() },
                title = "Recently Viewed",
                trailingContent = {
                    ArrowForward(onClick = { })
                },
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() }
            )
        }

        // Market category
        item {
            RoundedCategoryList(
                title = "Market",
                modifier = Modifier,
                categories = shopCategories,
                trailingContent = {
                    ArrowForward(onClick = { navController.navigate(Roots.SHOP_ROOT) })
                },
                onClick = {
                    //TODO : handle the click an other way
                    navController.navigate(MainFeatures.SHOP)
                }

            )

        }

        // Sponsored restaurant
        ColumnBusinessList(
            modifier = Modifier,
            businessData = if (sponsoredRestaurants.isNotEmpty()) listOf(sponsoredRestaurants[0]) else listOf(),
            title = { Divider() },
            onClick = { navigateToItem(navController,it) },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() }

        )

        // Sponsored shop
        item {
            if (sponsoredShops.isNotEmpty()) {
                val shopToPromote  = sponsoredShops[0]
                val item = getHomeShopItems(homeViewModel, homeRoomViewModel, shopToPromote,1)
                SmallBusinessList(
                    modifier = Modifier,
                    title = shopToPromote.title,
                    imageUrl = shopToPromote.imageUrl,
                    subtitle = shopToPromote.description,
                    onClick = { },
                    items = item,
                    onForward = { navigateToItem(navController, shopToPromote) },
                    onQuantityChange = { item, quantity ->
                        scope.launch {
                            homeRoomViewModel.insertOrderItem(item = item.toItemEntity(shopToPromote.id),quantity)
                            homeRoomViewModel.insertOrder(shopData = shopToPromote, id = shopToPromote.id,)
                        }
                    },
                )
            }
        }
        if (sponsors.isNotEmpty()) {
            item {
                //TODO : implement auto scroll
                RowListContainer(
                    title = "Sponsored",
                ) {
                    items(sponsors.size, key = { sponsors[it].id }) {
                        val sponsor = sponsors[it]
                        ImageContainer(
                            modifier = Modifier
                                .fillParentMaxWidth(0.95f)
                                .height(170.dp)
                                .animateItemPlacement(),
                            imageUrl = sponsor.imageUrl ?: "",
                            leftIcon = {}
                        )
                    }
                }
            }
        }
        item {
            if (sponsoredShops.isNotEmpty() && sponsoredShops.size > 1) {
                val shopToPromote = sponsoredShops[1]
                val item = getHomeShopItems(homeViewModel, homeRoomViewModel, shopToPromote,2)
                SmallBusinessList(
                    modifier = Modifier,
                    title = shopToPromote.title,
                    imageUrl = shopToPromote.imageUrl,
                    subtitle = shopToPromote.description,
                    onClick = { },
                    items = item,
                    onForward = { navigateToItem(navController, shopToPromote)  },
                    onQuantityChange = { item, quantity ->
                        scope.launch {
                            homeRoomViewModel.insertOrderItem(item = item.toItemEntity(shopToPromote.id),quantity)
                            homeRoomViewModel.insertOrder(shopData = shopToPromote, id = shopToPromote.id,)
                        }
                    },
                )
            }
        }


        // restaurants with promotion
        item {
            RowBusinessListWithNav(
                modifier = Modifier,
                businessData = restaurantList
                    .filter { it.isOpen && it.promotion != "" }
                    .ifEmpty { listOf() },
                title = "Reduction",
                trailingContent = {
                    ArrowForward(onClick = { })
                },
                navController = navController,
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }


            )
        }
        item {
            RowBusinessListWithNav(
                modifier = Modifier,
                businessData = shopList
                    .filter { it.isOpen && it.promotion != "" }
                    .ifEmpty { listOf() },
                title = "Reduction",
                trailingContent = {
                    ArrowForward(onClick = { })
                },
                navController = navController,
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }


            )
        }
        // and other sponsored restaurants
        ColumnBusinessList(
            modifier = Modifier,
            businessData = if (sponsoredRestaurants.isNotEmpty() && sponsoredRestaurants.size > 1) listOf(sponsoredRestaurants[1]) else listOf(),
            title = { Divider() },
            onClick = { navigateToItem(navController, it)  },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }


        )
        // not implemented yet
        item {
            RowBusinessListWithNav(
                businessData = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                title = "Favorites",
                trailingContent = {
                    ArrowForward(onClick = { })
                },
                onClick = {},
                navController = navController,
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }

            )
        }

        //TODO : thinks about mixing the restaurant
        // and shop in case the restaurnt list is so
        // long when will user see shops too

        // the list of all the restaurants
        val openRestaurants = restaurantList.filter { it.isOpen }
        val closeRestaurant = restaurantList.filter { !it.isOpen }

        val openShop = shopList.filter { it.isOpen }
        val closeShop = shopList.filter { !it.isOpen }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = openRestaurants,
            title = { TextWithIcon(title = "Tous les Restaurants", modifier = Modifier.fillMaxWidth() ){
                ArrowForward(){navController.navigate(Roots.RESTAURANT_ROOT)}
            } },
            onClick = { navigateToItem(navController, it)  },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }

        )
        ColumnBusinessList(
            modifier = Modifier,
            businessData = openShop ,
            title = { TextWithIcon(title = "Tous les Market", modifier = Modifier.fillMaxWidth() ){
                ArrowForward(){navController.navigate(Roots.SHOP_ROOT)}
            } },
            onClick = { navigateToItem(navController, it)  },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }

        )

        ColumnBusinessList(
            modifier = Modifier,
            businessData = closeShop + closeRestaurant,
            title = { TextWithIcon(title = "Les etablissements ferme", modifier = Modifier.fillMaxWidth() ){
            } },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b -> addFavorite(scope ,homeRoomViewModel,businessData,b) }
        )
    }
}

@Composable
fun getHomeShopItems(
    homeViewModel: HomeViewModel,
    homeRoomViewModel: HomeRoomViewModel,
    shopToPromote: BusinessData,
    key: Int
): List<Items> {
    // if there is no key the same list will be returned
    val items by (if (key == 1) homeViewModel.shopItems1 else homeViewModel.shopItems2).collectAsState()
    LaunchedEffect(key1 = shopToPromote) {
        homeViewModel.getShopItems(shopToPromote.id,key)
        homeRoomViewModel.getAllOrderByShopId(shopToPromote.id,key)

    }
    val orderItem by (if (key == 1) homeRoomViewModel.roomShopItems1 else homeRoomViewModel.roomShopItems2 ).collectAsState()
    val shopItems = items.asSequence().map { it.items }.flatten().map { it.items }
        .flatten().take(8).toList()

    return (shopItems).map {
        val order = orderItem.find { order -> order.title == it.title && order.price == it.price }
        if (order != null) {
            it.copy(quantity = order.quantity)
        } else {
            it
        }
    }
}

fun addFavorite (
    scope: CoroutineScope,
    roomVm: HomeRoomViewModel,
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

@Composable
fun RowBusinessListWithNav(
    navController: NavController,
    modifier: Modifier = Modifier,
    title: String = "Categories",
    businessData: List<BusinessData>,
    onClick: (BusinessData) -> Unit = {},
    color: Color? = null,
    favoriteBusiness: List<BusinessData> = emptyList(),
    onFavoriteClick: (BusinessData,Boolean) -> Unit = { _,_ -> },
    trailingContent: @Composable () -> Unit = {},
) {
    if (businessData.isEmpty()) return
    RowBusinessList(
        modifier = modifier,
        title = title,
        businessData = businessData,
        onClick = {
            navigateToItem(navController, it)
            onClick(it)
        },
        color = color,
        trailingContent = trailingContent,
        favoriteBusiness = favoriteBusiness,
        onFavoriteClick = onFavoriteClick,
    )
}

fun navigateToItem(navController: NavController, businessData: BusinessData) {
    navController.navigate(
        ( if (businessData.isRestaurant) MainFeatures.RESTAURANT_ITEM else MainFeatures.SHOP_ITEM ) +"/${businessData.id}"
    )
}


@Preview
@Composable
fun MainHome_() {
    DoumouniDronTheme {
        //MainHome(rememberNavController(), homeViewModel: HomeViewModel() )
    }
}