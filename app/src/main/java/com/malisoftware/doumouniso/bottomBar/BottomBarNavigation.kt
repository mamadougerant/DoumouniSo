package com.doumounidron.DoumouniDron.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import com.malisoftware.components.constants.NavConstant.Roots
import com.malisoftware.doumouniso.R


sealed class BottomBarNavigation (
    val route: String,
    val icon: Any? = null,
    val title: String,
    val iconOutlined: Any? = null,
) {
    data object Home : BottomBarNavigation(
        Roots.HOME_ROOT,
        Icons.Filled.Home,
        "Home",
        Icons.Outlined.Home
    )
    data object Orders : BottomBarNavigation(
        Roots.ORDER_ROOT,
        R.drawable.bills,
        "Commandes",
        R.drawable.billsoutlined
    )
    data object Cart : BottomBarNavigation(
        Roots.CART_ROOT,
        Icons.Filled.ShoppingCart,
        "Cart",
        Icons.Outlined.ShoppingCart
    )
    data object Profile : BottomBarNavigation(
        Roots.PROFILE_ROOT,
        Icons.Filled.Person,
        "Profile",
        Icons.Outlined.Person
    )
}
