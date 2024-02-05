package com.future.profile.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.malisoftware.futureapi.FeatureApi

interface ProfileApi: FeatureApi

class ProfileNav: ProfileApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        InternalProfileApi.registerGraph(navController, navGraphBuilder)
    }
}