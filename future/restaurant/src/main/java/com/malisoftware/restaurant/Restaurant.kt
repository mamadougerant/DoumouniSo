package com.malisoftware.restaurant

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.LazyLists.ChipList
import com.malisoftware.components.LazyLists.ColumnBusinessList
import com.malisoftware.components.LazyLists.ItemList
import com.malisoftware.components.LazyLists.RoundedCategoryList
import com.malisoftware.components.LazyLists.RowBusinessList
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.scaffold.HomeScaffoldWithBar
import com.malisoftware.components.constants.FilterConstant
import com.malisoftware.components.icons.ArrowForward
import com.malisoftware.components.icons.NavigationIcon
import com.malisoftware.model.BusinessData
import com.malisoftware.model.CategoryData
import com.doumounidron.theme.DoumouniDronTheme
import com.future.restaurant.RestaurantShimmer
import com.malisoftware.components.component.PriceSlider
import com.malisoftware.components.component.PriceSliderWithText
import com.malisoftware.components.container.ContinueOrder
import com.malisoftware.local.mappers.toBusinessData
import com.malisoftware.restaurant.viewModel.RestaurantOrderVM
import com.malisoftware.restaurant.viewModel.RestaurantViewModel
import com.malisoftware.restaurant.viewModel.RoomViewModel
import com.malisoftware.search.SearchContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Restaurant(
    navController: NavHostController,
    viewModel: RestaurantViewModel,
    roomVm: RoomViewModel,
    orderViewModel: RestaurantOrderVM,
) {

    // TODO: solve price filter issues
    LaunchedEffect(key1 = viewModel){
        viewModel.fetchAllData()
        roomVm.getRecentlyViewed()
    }
    LaunchedEffect(key1 = roomVm.favorite, ){
        roomVm.getFavorites()
    }
    LaunchedEffect(key1 = roomVm.orders, ){
        roomVm.getOrders()
    }


    val orders by roomVm.orders.collectAsState()


    val sponsors by viewModel.sponsors.collectAsState()
    val restaurantCategories by viewModel.restaurantCategoryList.collectAsState()
    val restaurantList by viewModel.restaurantList.collectAsState()
    val sponsoredRestaurants by viewModel.sponsorRestaurantList.collectAsState()
    val promotionRestaurants by viewModel.promotionRestaurantList.collectAsState()
    val recentlyViewed by roomVm.recentlyViewed.collectAsState()
    val favorites by roomVm.favorite.collectAsState()

    LaunchedEffect(key1 = sponsoredRestaurants ){
        orderViewModel.getRestaurantsItems(sponsoredRestaurants.map { it.id })
    }

    val restaurantsItems by orderViewModel.restaurantsItems.collectAsState()

    // use for the filter
    val filteredRestaurantList by viewModel.restaurantsByCategory.collectAsState()

    val orderList = orders.filter { it.restaurant.isRestaurant && it.restaurant.isOpen }.shuffled().take(1)


    val loading by viewModel.loading.collectAsState(true)

    val scope = rememberCoroutineScope()

    var searchQuery by remember { mutableStateOf("") }

    HomeScaffoldWithBar (
        modifier = Modifier.padding(horizontal = 10.dp),
        text = "Rechercher ",
        searchTabList = listOf(
            "Restaurant" to { SearchContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                onSearchResultClick = {searchQuery = it },
                query = searchQuery
            ) { MainFeatures.RESTAURANT_ITEM+"/${it.id}" }
            }
        ),
        initialQuery = searchQuery,
        onSearch = { searchQuery = it  },
        onQueryChange = { searchQuery = it },
        navIcon = {
            TextWithIcon(
                title = "Restaurant",
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
        shimmerContent = if (loading) { { RestaurantShimmer(Modifier.padding(it)) } } else null,

        filterContents = if ((filteredRestaurantList != null) && !loading) {{
            categoryAndChips(
                categories = restaurantCategories,
                viewModel = viewModel,
                scope = scope,
                onClick = { }
            )
            ColumnBusinessList(
                modifier = Modifier,
                businessData = filteredRestaurantList ?: emptyList(),
                title = {
                    ColumnBusinessListFilterTitle(
                        filteredRestaurantList = filteredRestaurantList,
                        viewModel = viewModel,
                        scope = scope,
                    )
                },
                onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b -> addFavorite(scope, roomVm, businessData, b) }
            )
            if ((filteredRestaurantList ?: emptyList()).isEmpty()) {
                item {
                    Text(
                        text = "No result found",
                        modifier = Modifier.fillMaxSize(),
                        textAlign = TextAlign.Center,
                    )
                }
            }
         }
        } else null,

    ) {
        categoryAndChips(
            categories = restaurantCategories,
            viewModel = viewModel,
            scope = scope,
        )
        item {
            RowBusinessListWithNav(
                    navController = navController,
                    modifier = Modifier,
                    businessData = recentlyViewed.map{ it.toBusinessData() },
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
            businessData = sponsoredRestaurants,
            title = { Divider() },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b ->
                addFavorite(scope, roomVm, businessData, b)
            }
        )
        item {
            (restaurantsItems).forEachIndexed { index, _ ->
                val oneRestaurantsItems = restaurantsItems.random()
                val items = oneRestaurantsItems.items.map { it.items }.flatten().take(5)
                ItemList(
                    title = if (index==0) "Quoi Manger ?" else "",
                    modifier = Modifier,
                    items = items,
                    showAddButton = false,
                    onClick = {
                        navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${oneRestaurantsItems.businessId}")
                    }
                ) {

                }
            }
        }
        item {
            val favoriteBusiness = favorites.map { it.favoriteBusiness }.filter { it.isRestaurant }.map { it.toBusinessData() }
            RowBusinessListWithNav(
                modifier = Modifier,
                businessData = favoriteBusiness,
                title = "Favories",
                trailingContent = {
                    ArrowForward(onClick = {
                        viewModel.setRestaurantsByCategory(favoriteBusiness)
                    })
                },
                navController = navController,
                onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b ->
                    addFavorite(scope, roomVm, businessData, b)
                }
            )
        }
        item {
            RowBusinessListWithNav(
                modifier = Modifier,
                businessData = promotionRestaurants,
                title = "Promotions",
                trailingContent = {
                    ArrowForward(onClick = {
                        viewModel.setRestaurantsByCategory(promotionRestaurants)
                    })
                },
                navController = navController,
                onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
                favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
                onFavoriteClick = { businessData, b ->
                    addFavorite(scope, roomVm, businessData, b)
                }
            )
        }

        val openRestaurant = restaurantList.filter { it.isOpen }
        val closeRestaurant = restaurantList.filter { !it.isOpen }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = openRestaurant + closeRestaurant,
            title = { TextWithIcon(title = "Tous les Restaurants", modifier = Modifier.fillMaxWidth() ){} },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
            favoriteBusiness = favorites.map { it.favoriteBusiness }.map { it.toBusinessData() },
            onFavoriteClick = { businessData, b ->
                addFavorite(scope, roomVm, businessData, b)
            }
        )

    }
}

fun <T> randomList(list: List<T>, size: Int = 2): List<T> {
    return list.shuffled().take(size).toSet().toList()
}


fun addFavorite (
    scope: CoroutineScope,
    roomVm: RoomViewModel,
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
    navController: NavHostController,
    modifier: Modifier = Modifier,
    title: String = "Categories",
    businessData: List<BusinessData>,
    favoriteBusiness: List<BusinessData> = emptyList(),
    onFavoriteClick: (BusinessData, Boolean) -> Unit = { businessData: BusinessData, b: Boolean -> },
    onClick: (BusinessData) -> Unit = {},
    color: Color? = null,
    trailingContent: @Composable () -> Unit = {},
) {
    if (businessData.isEmpty()) return
    RowBusinessList(
        modifier = modifier,
        title = title,
        businessData = businessData,
        onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}"); onClick(it) },
        color = color,
        trailingContent = trailingContent,
        favoriteBusiness = favoriteBusiness,
        onFavoriteClick = onFavoriteClick
    )
}

@Composable
fun ColumnBusinessListFilterTitle(
    filteredRestaurantList: List<BusinessData>?,
    viewModel: RestaurantViewModel,
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
                    viewModel.getRestaurantListByCategory("")
                    viewModel.clearFilter()
                }

            },
            textDecoration = TextDecoration.Underline
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun LazyListScope.categoryAndChips(
    categories: List<CategoryData>,
    viewModel: RestaurantViewModel,
    scope: CoroutineScope,
    onClick: () -> Unit = {},
){

    item {
        RoundedCategoryList(
            title = null,
            modifier = Modifier.padding(top = 10.dp),
            categories = categories,
            onClick = {
                scope.launch { viewModel.getRestaurantListByCategory(it.title) }
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
        if (openSheet && chipList.isNotEmpty()) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.removeFilter(chipList.last()) },
                dragHandle = {
                    TextWithIcon(
                        title = chipList.last(),
                        modifier = Modifier
                            .padding(10.dp)
                            .padding(top = 20.dp)
                    ) {  }
                },
            ) {
                when (chipList.last()){
                    FilterConstant.PRICE.title -> {
                        PriceSlider(
                            onClick = { start, end ->
                                viewModel.filterPrice(start.toDouble(), end.toDouble())
                                viewModel.closeSheet()
                            }
                        )
                    }
                    FilterConstant.DELIVERY_FEE.title -> {
                        PriceSlider(
                            onClick = { start, end ->
                                viewModel.filterDeliveryFee(start.toDouble(), end.toDouble())
                                viewModel.closeSheet()
                            }
                        )
                    }
                    FilterConstant.DELIVERY_TIME.title -> {
                        PriceSliderWithText(
                            data = listOf("10","20","30","40","50"),
                            onClick = { start, end ->
                                viewModel.filterDeliveryTime(start.toInt(), end.toInt())
                                viewModel.closeSheet()
                            }
                        )
                    }
                    FilterConstant.RATING.title -> {
                        PriceSliderWithText(
                            data = listOf("0","1","2","3","4","5"),
                            onClick = { start, end ->
                                viewModel.filterRating(start.toDouble()*0.05, end.toDouble()*0.05)
                                viewModel.closeSheet()
                            }
                        )
                    }
                    else -> { viewModel.closeSheet() }
                }
            }
        }
    }
}



@Preview
@Composable
fun Restaurant_() {
    DoumouniDronTheme {
       // Restaurant(rememberNavController(), viewModel)
    }
}