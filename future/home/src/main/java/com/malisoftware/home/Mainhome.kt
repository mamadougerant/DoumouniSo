package com.malisoftware.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
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
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.components.container.StoreContainer
import com.malisoftware.components.icons.ArrowForward
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items
import com.doumounidron.theme.DoumouniDronTheme
import com.future.home.HomeShimmer
import com.malisoftware.home.viewModel.HomeViewModel
import com.future.search.RestaurantSearchContent
import com.malisoftware.home.viewModel.HomeRoomViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainHome(
    navController: NavController,
    homeViewModel: HomeViewModel,
    homeRoomViewModel: HomeRoomViewModel,
) {
    LaunchedEffect(key1 = homeViewModel){
        homeViewModel.getSponsors()
        homeViewModel.getStoreList()
        homeViewModel.getRestaurantCategoryList()
        homeViewModel.getShopCategoryList()
        homeViewModel.getRestaurantList()
        homeViewModel.getShopList()
        homeViewModel.getSponsoredRestaurant()
        homeViewModel.getSponsoredShop()

    }
    val sponsors by homeViewModel.sponsors.collectAsState()
    val stores by homeViewModel.storeList.collectAsState()
    val restaurantCategories by homeViewModel.restaurantCategoryList.collectAsState()
    val shopCategories by homeViewModel.shopCategoryList.collectAsState()
    val restaurantList by homeViewModel.restaurantList.collectAsState()
    val shopList by homeViewModel.shopList.collectAsState()

    val sponsoredRestaurants by homeViewModel.sponsorRestaurantList.collectAsState()
    val sponsoredShops by homeViewModel.sponsorShopList.collectAsState()

    val loading by homeViewModel.loading.collectAsState(true)

    Log.d("MainHome3", "loading: ${loading}")

    HomeScaffoldWithBar (
        modifier = Modifier.padding(horizontal = 10.dp),
        text = "Rechercher ",
        searchTabList = listOf(
            "Restaurant" to { RestaurantSearchContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) },
            "Market" to { RestaurantSearchContent(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                searchResult = emptyList()

            ) },

        ),
        onQueryChange = { },
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
            )
        }

        // orders in the cart
        item {
            Column (
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(0.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                TextWithIcon(
                    title = "Continue Order",
                    modifier = Modifier.fillMaxWidth()
                ) {}
                OutlinedCard() {
                    SmallBusinessContainer(
                        modifier = Modifier.padding(5.dp),
                        imageUrl = "",
                        title = "Burger King",
                        subtitle = "null",
                    ) {
                        ArrowForward(onClick = { })
                    }
                }
            }
        }

        // mix of the restaurants and shops
        //navigate to the restaurant and shop on click
        item {
            RowBusinessListWithNav(
                navController = navController,
                modifier = Modifier,
                businessData = listOf(
                    BusinessData(
                        id  = "1",
                    ),
                    BusinessData(
                        id  = "2",
                    ),
                    BusinessData(
                        id  = "3",
                    ),
                    BusinessData(
                        id  = "4",
                    ),
                ),
                title = "Recently Viewed",
                trailingContent = {
                    ArrowForward(onClick = { })
                }
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
                }
            )

        }

        // Sponsored restaurant
        ColumnBusinessList(
            modifier = Modifier,
            businessData = if (sponsoredRestaurants.isNotEmpty()) listOf(sponsoredRestaurants[0]) else listOf(),
            title = { Divider() },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") }
        )

        // Sponsored shop
        item {
            if (sponsoredShops.isNotEmpty())
                SmallBusinessList(
                    modifier = Modifier,
                    title = sponsoredShops[0].title,
                    imageUrl = sponsoredShops[0].imageUrl,
                    subtitle = sponsoredShops[0].description,
                    onClick = { },
                    items = listOf(
                        Items(),
                        Items(),
                        Items(),
                        Items(),
                    ),
                    onForward = { navController.navigate(MainFeatures.SHOP_ITEM+"/${sponsoredShops[0].id}") },
                    onQuantityChange = {},
                )
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
            if (sponsoredShops.isNotEmpty() && sponsoredShops.size > 1)
                SmallBusinessList(
                    modifier = Modifier,
                    title = sponsoredShops[1].title,
                    imageUrl = sponsoredShops[1].imageUrl,
                    subtitle = sponsoredShops[1].description,
                    onClick = { },
                    items = listOf(
                        Items(),
                        Items(),
                        Items(),
                        Items(),
                    ),
                    onForward = { navController.navigate(MainFeatures.SHOP_ITEM+"/${sponsoredShops[1].id}") },
                    onQuantityChange = {},
                )
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
                navController = navController

            )
        }
        // and other sponsored restaurants
        ColumnBusinessList(
            modifier = Modifier,
            businessData = if (sponsoredRestaurants.isNotEmpty() && sponsoredRestaurants.size > 1) listOf(sponsoredRestaurants[1]) else listOf(),
            title = { Divider() },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
        )
        // not implemented yet
        item {
            RowBusinessListWithNav(
                businessData = listOf(
                    BusinessData(),
                    BusinessData(),
                    BusinessData(),
                    BusinessData(),
                ),
                title = "Pour Vous",
                trailingContent = {
                    ArrowForward(onClick = { })
                },
                navController = navController,

                )
        }

        //TODO : thinks about mixing the restaurant
        // and shop in case the restaurnt list is so
        // long when will user see shops too

        // the list of all the restaurants
        val openRestaurants = restaurantList.filter { it.isOpen }
        val closeRestaurant = restaurantList.filter { !it.isOpen }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = openRestaurants + closeRestaurant,
            title = { TextWithIcon(title = "Tous les Restaurants", modifier = Modifier.fillMaxWidth() ){
                ArrowForward(){navController.navigate(Roots.RESTAURANT_ROOT)}
            } },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
        )
        // the list of all the shops
        val openShop = shopList.filter { it.isOpen }
        val closeShop = shopList.filter { !it.isOpen }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = openShop + closeShop,
            title = { TextWithIcon(title = "Tous les Market", modifier = Modifier.fillMaxWidth() ){
                ArrowForward(){navController.navigate(Roots.SHOP_ROOT)}
            } },
        )
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
        onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}"); onClick(it) },
        color = color,
        trailingContent = trailingContent,
        favoriteBusiness = favoriteBusiness,
        onFavoriteClick = onFavoriteClick,
    )
}


@Preview
@Composable
fun MainHome_() {
    DoumouniDronTheme {
        //MainHome(rememberNavController(), homeViewModel: HomeViewModel() )
    }
}