package com.malisoftware.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.malisoftware.futureapi.FeatureApi

interface HomeApi: FeatureApi

class HomeNav: HomeApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalHomeApi.registerGraph(navController, navGraphBuilder)
    }
}