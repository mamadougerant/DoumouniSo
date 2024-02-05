package com.malisoftware.orders

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.malisoftware.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Order(navController: NavHostController) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Commandes",
                        style = AppTheme.typography.titleLarge,
                    )
                },

            )
        },
    ) {
        LazyColumn(
            contentPadding = it
        ) {

        }
    }
}