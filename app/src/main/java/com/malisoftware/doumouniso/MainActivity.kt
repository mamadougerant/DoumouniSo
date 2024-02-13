package com.malisoftware.doumouniso

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.asFlow
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.malisoftware.doumouniso.bottomBar.NavigationBottomBar
import com.malisoftware.doumouniso.navigation.AppNavigation
import com.malisoftware.doumouniso.navigation.NavigationProvider
import com.doumounidron.theme.DoumouniDronTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.malisoftware.local.repository.LocalRepository
import com.malisoftware.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationProvider: NavigationProvider
    
    @Inject
    lateinit var room: LocalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO try catch and ask to signal a problem
        setContent {
            DoumouniDronTheme {
                val navController = rememberNavController()
                val systemUiController = rememberSystemUiController()
                if(isSystemInDarkTheme()){
                    systemUiController.setSystemBarsColor(Color.Black)
                    systemUiController.setStatusBarColor(Color.Black)
                }else{
                    systemUiController.setSystemBarsColor(Color.White)
                    systemUiController.setStatusBarColor(Color.White)
                }
                App(
                    navController = navController, 
                    navigationProvider = navigationProvider,
                    repositoryRoom = room
                )
            }
        }
    }
}

@Composable
fun App(
    navController: NavHostController,
    navigationProvider: NavigationProvider,
    repositoryRoom: LocalRepository
) {
    val orderCount = repositoryRoom.orderCount().asFlow().collectAsState(initial = 0)
    Scaffold (
        bottomBar = {
            NavigationBottomBar(
                navController = navController,
                orderCount = orderCount.value
            )
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            //color = MaterialTheme.colorScheme.background
        ) {
            AppNavigation(
                navController = navController,
                navigationProvider = navigationProvider
            )
        }
    }

}
