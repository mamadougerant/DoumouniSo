package com.malisoftware.orders.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.common.components.constants.NavConstant.MainFeatures
import com.common.components.constants.NavConstant.Roots
import com.future.orders.navigation.OrderApi
import com.malisoftware.orders.Order

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
                Order(navController = navController,)
            }

        }

    }
}