package com.malisoftware.doumouniso.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.malisoftware.components.constants.NavConstant.Roots

@Composable
fun AppNavigation(
    navController: NavHostController,
    navigationProvider: NavigationProvider
) {
    NavHost(
        navController = navController,
        startDestination = Roots.HOME_ROOT,
        route = Roots.ROOT
    ){
        navigationProvider.homeApi.registerGraph(navController, this)
        navigationProvider.restaurantApi.registerGraph(navController, this)
        navigationProvider.shopApi.registerGraph(navController, this)
        navigationProvider.cartApi.registerGraph(navController, this)
        navigationProvider.profileApi.registerGraph(navController, this)
        navigationProvider.orderApi.registerGraph(navController, this)
    }

}