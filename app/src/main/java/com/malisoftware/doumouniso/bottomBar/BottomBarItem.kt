package com.malisoftware.doumouniso.bottomBar

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.doumounidron.DoumouniDron.bottomBar.BottomBarNavigation
import com.malisoftware.components.constants.NavConstant.MainFeatures
import com.malisoftware.theme.AppTheme
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.BottomBarItem(
    navController: NavController,
    bottomBarNavigation: BottomBarNavigation,
    isSelected: Boolean,
    applyBadge: Boolean = false,
    badgeNum: Int = 0,
) {
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination
    NavigationBarItem(
        modifier = Modifier.background(if (isSystemInDarkTheme())Color.Black else Color.White),
        selected = isSelected,
        onClick = {
            if (bottomBarNavigation.route==BottomBarNavigation.Home.route &&
                currentRoute?.hierarchy?.any { it.route != BottomBarNavigation.Home.route } == true){
                navController.navigateUp()
            }
            if (bottomBarNavigation.route==BottomBarNavigation.Cart.route &&
                currentRoute?.hierarchy?.any { it.route != BottomBarNavigation.Cart.route } == true){
                navController.navigateUp()
            }
            scope.launch {
                navController.navigate(bottomBarNavigation.route){
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                    anim {  }
                }
            }
        },
        icon = {
            BadgedBox(
                badge = {
                    if (applyBadge && badgeNum != 0)
                        Badge(
                            containerColor = AppTheme.colors.primary
                        ) {
                            Text(
                                text = badgeNum.coerceAtMost(9).toString() +
                                        if (badgeNum > 9) "+" else "",
                                style = AppTheme.typography.titleSmall,
                            )
                        }
                } ,
                modifier = Modifier
            ) {
                val icon = if (!isSelected) bottomBarNavigation.iconOutlined else bottomBarNavigation.icon
                if (icon is ImageVector)
                    Icon(
                        imageVector = icon,
                        contentDescription = bottomBarNavigation.title,
                    )
                else if (icon is Int){
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = bottomBarNavigation.title,
                    )
                }
            }
        },
        label = {
            Text(bottomBarNavigation.title)
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            selectedTextColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            unselectedIconColor = Color.LightGray,
            unselectedTextColor = Color.LightGray,
            disabledIconColor = Color.Unspecified,
            disabledTextColor = Color.Unspecified,
            indicatorColor = if (isSystemInDarkTheme()) Color.Black else Color.White,
        )
    )

}