package com.future.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.future.profile.Profile

internal object InternalProfileApi: ProfileApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatures.PROFILE,
            route = Roots.PROFILE_ROOT
        ){
            composable(MainFeatures.PROFILE){
                Profile(navController = navController,)
            }

        }

    }
}