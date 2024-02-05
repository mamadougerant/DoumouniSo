package com.malisoftware.cart.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.common.components.constants.NavConstant.MainFeatures
import com.common.components.constants.NavConstant.Roots
import com.future.cart.Cart
import com.future.cart.CheckOut
import com.malisoftware.cart.navigation.CartApi

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
                Cart(navController = navController,)

            }
            composable(MainFeatures.CART_CHECKOUT + "/{id}"){
                CheckOut(navController = navController, orderId = it.arguments?.getString("id") ?: "")
            }
        }

    }
}