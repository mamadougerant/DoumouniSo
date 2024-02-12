package com.malisoftware.orders.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.future.orders.navigation.OrderApi
import com.malisoftware.orders.Order
import com.malisoftware.orders.OrderViewModel

internal object InternalOrderApi: OrderApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatures.ORDER,
            route = Roots.ORDER_ROOT
        ){
            composable(MainFeatures.ORDER){
                val orderViewModel: OrderViewModel = hiltViewModel()
                Order(navController = navController,orderViewModel = orderViewModel)
            }

        }

    }
}