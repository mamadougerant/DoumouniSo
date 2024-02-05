package com.malisoftware.cart.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.common.components.constants.NavConstant.MainFeatures
import com.common.components.constants.NavConstant.Roots
import com.future.cart.Cart
import com.future.cart.CheckOut
import com.malisoftware.cart.CartRoomViewModel

internal object InternalCartApi: CartApi {
    override fun registerGraph(
        navController: NavHostController,
        navGraphBuilder: NavGraphBuilder
    ) {
        navGraphBuilder.navigation(
            startDestination = MainFeatures.CART,
            route = Roots.CART_ROOT
        ){
            composable(MainFeatures.CART){
                val cartVm: CartRoomViewModel = hiltViewModel()
                Cart(navController = navController, cartVm = cartVm)

            }
            composable(MainFeatures.CART_CHECKOUT + "/{id}"){
                val cartVm: CartRoomViewModel = hiltViewModel()
                CheckOut(
                    navController = navController,
                    orderId = it.arguments?.getString("id") ?: "",
                    cartVm = cartVm
                )
            }
        }

    }
}