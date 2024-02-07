package com.malisoftware.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.future.home.MainHome
import com.malisoftware.home.viewModel.HomeViewModel

internal object InternalHomeApi: HomeApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatures.HOME,
            route = Roots.HOME_ROOT
        ){
            composable(MainFeatures.HOME){
                val homeViewModel: HomeViewModel = hiltViewModel()
                MainHome(navController = navController,homeViewModel = homeViewModel)
            }
        }

    }
}