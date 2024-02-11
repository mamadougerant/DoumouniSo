package com.malisoftware.restaurant.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.malisoftware.restaurant.Restaurant
import com.malisoftware.restaurant.RestaurantItem
import com.malisoftware.restaurant.viewModel.RestaurantOrderVM
import com.malisoftware.restaurant.viewModel.RestaurantViewModel
import com.malisoftware.restaurant.viewModel.RoomViewModel


internal object InternalRestaurantApi: RestaurantApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatures.RESTAURANT,
            route = Roots.RESTAURANT_ROOT
        ){
            composable(MainFeatures.RESTAURANT){
                val viewModel: RestaurantViewModel = hiltViewModel()
                val orderViewModel: RestaurantOrderVM = hiltViewModel()
                val roomVm: RoomViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
                Restaurant(
                    navController = navController,
                    viewModel = viewModel,
                    roomVm = roomVm,
                    orderViewModel = orderViewModel
                )
            }
            composable(MainFeatures.RESTAURANT_ITEM + "/{id}"){ backStack->
                val viewModel: RestaurantViewModel = hiltViewModel()
                val orderViewModel: RestaurantOrderVM = hiltViewModel()
                val roomVm: RoomViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
                RestaurantItem(
                    navController = navController,
                    id = backStack.arguments?.getString("id") ?: "",
                    viewModel = viewModel,
                    orderViewModel = orderViewModel,
                    roomVm = roomVm
                )
            }
        }

    }
}