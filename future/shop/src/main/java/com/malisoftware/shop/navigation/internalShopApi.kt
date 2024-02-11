package com.malisoftware.shop.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.malisoftware.shop.ShopItem
import com.malisoftware.shop.ShopScreen
import com.future.shop.viewModel.ShopViewModel
import com.malisoftware.shop.viewModel.ShopOrderVm
import com.malisoftware.shop.viewModel.ShopRoomVm

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
                val shopOrderVM: ShopOrderVm = hiltViewModel()
                val shopRoomVm: ShopRoomVm = hiltViewModel()
                ShopScreen(
                    navController = navController,
                    viewModel = viewModel,
                    shopOrderVM = shopOrderVM,
                    shopRoomVm = shopRoomVm
                )
            }
            composable(MainFeatures.SHOP_ITEM+ "/{id}"){ backStack->
                val viewModel: ShopViewModel = hiltViewModel()
                val shopOrderVM: ShopOrderVm = hiltViewModel()
                val shopRoomVm: ShopRoomVm = hiltViewModel()
                ShopItem(
                    navController = navController,
                    id = backStack.arguments?.getString("id") ?: "",
                    viewModel = viewModel,
                    shopOrderVM = shopOrderVM,
                    shopRoomVm = shopRoomVm
                )
            }
        }

    }
}