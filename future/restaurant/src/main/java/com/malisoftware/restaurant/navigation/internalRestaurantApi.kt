package com.malisoftware.restaurant.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.common.components.constants.NavConstant.MainFeatures
import com.common.components.constants.NavConstant.Roots
import com.future.restaurant.Restaurant
import com.malisoftware.restaurant.RestaurantItem
import com.future.restaurant.viewModel.RestaurantOrderVM
import com.future.restaurant.viewModel.RestaurantViewModel


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
                Restaurant(navController = navController, viewModel = viewModel)
            }
            composable(MainFeatures.RESTAURANT_ITEM + "/{id}"){ backStack->
                val viewModel: RestaurantViewModel = hiltViewModel()
                val orderViewModel: RestaurantOrderVM = hiltViewModel()
                //val roomVm: RestaurantRoomVM = hiltViewModel()
                RestaurantItem(
                    navController = navController,
                    id = backStack.arguments?.getString("id") ?: "",
                    viewModel = viewModel,
                    orderViewModel = orderViewModel,
                    //roomVm = roomVm
                )
            }
        }

    }
}