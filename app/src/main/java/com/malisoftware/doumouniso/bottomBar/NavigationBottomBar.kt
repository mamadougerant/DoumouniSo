package com.malisoftware.doumouniso.bottomBar

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.height
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.doumounidron.DoumouniDron.bottomBar.BottomBarNavigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.theme.AppTheme

@Composable
fun NavigationBottomBar(
    navController: NavController,
    orderCount: Int = 0
) {
    // if current destination is shop on va dire bottomBarNavigationItems = listOF(Home, Favorites, Cart, Profile)
    // pour shop donc comme ca va mettre les buttom bar de shop comme pour restaurant automatiquement on va
    // melanger les cart des shop et restaurant etc
    /// if (currentRoute?.hierarchy?.any { it.route == RESTAURANT_ROUTE } == true
    val bottomBarNavigationItems = listOf(
        BottomBarNavigation.Home,
        BottomBarNavigation.Orders,
        BottomBarNavigation.Cart,
        BottomBarNavigation.Profile,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    if (currentRoute?.hierarchy?.any { it.route == MainFeatures.CART_ITEM + "/{id}" } == false &&
        !currentRoute.hierarchy.any { it.route == MainFeatures.CART_CHECKOUT + "/{id}" } &&
        !currentRoute.hierarchy.any { it.route == "" }
    ) {
        NavigationBar(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = AppTheme.colors.background
                )
                .height(70.dp),
            contentColor = Color.Unspecified,
            containerColor = if (isSystemInDarkTheme())Color.Black else Color.White ,
        ) {
            bottomBarNavigationItems.forEach { bottomBarNavigation ->
                val isSelected = currentRoute.hierarchy.any { it.route == bottomBarNavigation.route }
                BottomBarItem(
                    navController = navController,
                    bottomBarNavigation = bottomBarNavigation,
                    isSelected = isSelected,
                    applyBadge = bottomBarNavigation == BottomBarNavigation.Cart,
                    badgeNum = orderCount
                )
            }
        }
    }
}