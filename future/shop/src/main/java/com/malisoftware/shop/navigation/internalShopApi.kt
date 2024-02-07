package com.malisoftware.shop.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.future.shop.ShopItem
import com.future.shop.ShopScreen
import com.future.shop.viewModel.ShopViewModel

internal object InternalShopApi: ShopApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatures.SHOP,
            route = Roots.SHOP_ROOT
        ){
            composable(MainFeatures.SHOP){
                val viewModel: ShopViewModel = hiltViewModel()
                ShopScreen(navController = navController, viewModel = viewModel)
            }
            composable(MainFeatures.SHOP_ITEM+ "/{id}"){ backStack->
                val viewModel: ShopViewModel = hiltViewModel()
                ShopItem(
                    navController = navController,
                    id = backStack.arguments?.getString("id") ?: "",
                    viewModel = viewModel
                )
            }
        }

    }
}