package com.malisoftware.restaurant.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.malisoftware.futureapi.FeatureApi


interface RestaurantApi: FeatureApi

class RestaurantNav: RestaurantApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalRestaurantApi.registerGraph(navController, navGraphBuilder)
    }
}