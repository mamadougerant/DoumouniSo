package com.future.orders.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.malisoftware.futureapi.FeatureApi
import com.malisoftware.orders.navigation.InternalOrderApi


interface OrderApi: FeatureApi

class OrderNav : OrderApi{
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalOrderApi.registerGraph(navController, navGraphBuilder)
    }
}