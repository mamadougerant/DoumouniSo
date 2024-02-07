package com.malisoftware.cart.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.components.constants.NavConstant.Roots
import com.malisoftware.cart.Cart
import com.future.cart.CheckOut
import com.malisoftware.cart.CartItems
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
                val cartVm: CartRoomViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
                Cart(navController = navController, cartVm = cartVm)

            }
            composable(MainFeatures.CART_ITEM + "/{id}"){
                val cartVm: CartRoomViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
                CartItems(
                    navController = navController,
                    id = it.arguments?.getString("id") ?: "",
                    viewModel = cartVm
                )
            }
            composable(MainFeatures.CART_CHECKOUT + "/{id}"){
                val cartVm: CartRoomViewModel = hiltViewModel(LocalContext.current as ComponentActivity)
                CheckOut(
                    navController = navController,
                    orderId = it.arguments?.getString("id") ?: "",
                    cartVm = cartVm
                )
            }
        }

    }


}