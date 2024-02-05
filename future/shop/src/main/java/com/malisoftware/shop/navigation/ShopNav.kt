package com.malisoftware.shop.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.malisoftware.futureapi.FeatureApi

interface ShopApi: FeatureApi

class ShopNav: ShopApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalShopApi.registerGraph(navController, navGraphBuilder)
    }

}