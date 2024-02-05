package com.future.restaurant

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.common.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.LazyLists.ChipList
import com.malisoftware.components.LazyLists.ColumnBusinessList
import com.malisoftware.components.LazyLists.ItemList
import com.malisoftware.components.LazyLists.RoundedCategoryList
import com.malisoftware.components.LazyLists.RowBusinessList
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.component.RangeSliderWithData
import com.malisoftware.components.component.RangeSliderWithGraph
import com.malisoftware.components.component.scaffold.HomeScaffoldWithBar
import com.malisoftware.components.constants.FilterConstant
import com.malisoftware.components.container.SmallBusinessContainer
import com.malisoftware.components.container.icons.ArrowForward
import com.malisoftware.components.container.icons.NavigationIcon
import com.malisoftware.components.formatPrice
import com.malisoftware.model.BusinessData
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme
import com.doumounidron.theme.DoumouniDronTheme
import com.future.restaurant.viewModel.RestaurantViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun Restaurant(
    navController: NavHostController,
    viewModel: RestaurantViewModel
) {

    // TODO: format price
    LaunchedEffect(key1 = viewModel){
        viewModel.getSponsors()
        viewModel.getRestaurantCategoryList()
        viewModel.getRestaurantList()
        viewModel.getSponsoredRestaurant()
        viewModel.getDiscountedRestaurant()
    }

    val sponsors by viewModel.sponsors.collectAsState()
    val stores by viewModel.storeList.collectAsState()
    val restaurantCategories by viewModel.restaurantCategoryList.collectAsState()
    val restaurantList by viewModel.restaurantList.collectAsState()
    val sponsoredRestaurants by viewModel.sponsorRestaurantList.collectAsState()
    val promotionRestaurants by viewModel.promotionRestaurantList.collectAsState()

    val filteredRestaurantList by viewModel.restaurantsByCategory.collectAsState()


    val loading by viewModel.loading.collectAsState(true)

    val scope = rememberCoroutineScope()

    Log.d("Restaurant", "filteredRestaurantList: $filteredRestaurantList")

    HomeScaffoldWithBar (
        modifier = Modifier.padding(horizontal = 10.dp),
        text = "Rechercher ",
        searchTabList = listOf(
            "Restaurant" to { },
        ),
        onQueryChange = { },
        navIcon = {
            Row (
                modifier = Modifier
                    .padding(horizontal = 0.dp)
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ){
                NavigationIcon(
                    modifier = Modifier.height(30.dp),
                    onClick = { navController.navigateUp() },
                    color = Color.Unspecified
                )
                TextWithIcon(
                    title = "Restaurant",
                    modifier = Modifier.fillMaxWidth()
                ) {}
            }
        },
        shimmerContent = if (loading) { { RestaurantShimmer(Modifier.padding(it)) } } else null,

        filterContents = if ((filteredRestaurantList != null) && !loading) {{
            if (filteredRestaurantList != null) {
                categoryAndChips(
                    categories = restaurantCategories,
                    viewModel = viewModel,
                    scope = scope,
                    onClick = { }
                )
                ColumnBusinessList(
                    modifier = Modifier,
                    businessData = filteredRestaurantList!!,
                    title = {
                        ColumnBusinessListFilterTitle(
                            filteredRestaurantList = filteredRestaurantList,
                            viewModel = viewModel,
                            scope = scope,
                        )
                    },
                    onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
                )
                if (filteredRestaurantList!!.isEmpty()) {
                    item {
                        Text(
                            text = "No result found",
                            modifier = Modifier.fillMaxSize(),
                            textAlign = TextAlign.Center,
                        )
                    }
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
                businessData = listOf(
                    BusinessData(
                        id  = "6",
                    ),
                    BusinessData(
                        id  = "7",
                    ),
                    BusinessData(
                        id  = "8",
                    ),
                    BusinessData(
                        id  = "9",
                    ),
                ),
                title = "Recently Viewed",
                trailingContent = {
                    ArrowForward(onClick = { })
                },

            )
        }
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

        ColumnBusinessList(
            modifier = Modifier,
            businessData = sponsoredRestaurants,
            title = { Divider() },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
        )
        item {
            // show ordering modal
            ItemList (
                title = "Quoi Manger ?",
                modifier = Modifier,
                items = listOf(
                    Items(),
                    Items(),
                    Items(),
                    Items(),
                ),
                onQuantityChange = { },
            ) {

            }
        }
        item {
            RowBusinessListWithNav(
                modifier = Modifier,
                businessData = listOf(
                    BusinessData(),
                    BusinessData(),
                    BusinessData(),
                    BusinessData(),
                ),
                title = "Pour vous",
                trailingContent = {
                    ArrowForward(onClick = { })
                },
                navController = navController,
                onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
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
            )
        }
        ColumnBusinessList(
            modifier = Modifier,
            businessData = restaurantList,
            title = { TextWithIcon(title = "Tous les Restaurants", modifier = Modifier.fillMaxWidth() ){} },
            onClick = { navController.navigate(MainFeatures.RESTAURANT_ITEM+"/${it.id}") },
        )

    }
}

@Composable
fun RowBusinessListWithNav(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    title: String = "Categories",
    businessData: List<BusinessData>,
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
        @Composable
        fun PriceSlider(
            steps: Int = 50,
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

@Preview
@Composable
fun Restaurant_() {
    DoumouniDronTheme {
       // Restaurant(rememberNavController(), viewModel)
    }
}