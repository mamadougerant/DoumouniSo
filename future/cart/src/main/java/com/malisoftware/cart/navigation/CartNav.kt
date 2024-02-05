package com.malisoftware.cart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.malisoftware.futureapi.FeatureApi

interface CartApi: FeatureApi

class CartNav: CartApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalCartApi.registerGraph(navController, navGraphBuilder)
    }
}