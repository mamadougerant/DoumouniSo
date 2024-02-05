package com.malisoftware.doumouniso

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.malisoftware.doumouniso.bottomBar.NavigationBottomBar
import com.malisoftware.doumouniso.navigation.AppNavigation
import com.malisoftware.doumouniso.navigation.NavigationProvider
import com.doumounidron.theme.DoumouniDronTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationProvider: NavigationProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DoumouniDronTheme {
                val navController = rememberNavController()
                App(navController = navController, navigationProvider = navigationProvider)
            }
        }
    }
}

@Composable
fun App(navController: NavHostController, navigationProvider: NavigationProvider) {
    Scaffold (
        bottomBar = {
            NavigationBottomBar(navController = navController)
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize().padding(it),
            //color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation(
                navController = navController,
                navigationProvider = navigationProvider
            )
        }
    }
    Log.d("Repository" , "getRestaurantListBySearch: ${LocalTime.now()}")
    Log.d("Repository", "getRestaurantListBySearch: ${LocalDate.now().dayOfWeek}")
}
