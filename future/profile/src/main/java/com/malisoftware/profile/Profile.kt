package com.future.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.malisoftware.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(navController: NavController) {

    Scaffold (
        topBar = {
            TopAppBar(

                title = {
                    Text(
                        text = "Profile",
                        style = AppTheme.typography.titleLarge,
                    )
                },

            )
        }

    ) {
        LazyColumn(
            contentPadding = it
        ) {
            item {
                 ProfileInfo()
            }
            item {
                Text(
                    text = "L'ecran de profile est en cours de developpement",
                    style = AppTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 26.dp, vertical = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

        }
    }
}

@Composable
fun ProfileInfo(
    text: String = "Doumouni",
) {
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 26.dp, vertical = 16.dp)
            .height(200.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White),
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color.Black),
            ) {
                Text(
                    text = text.first().toString(),
                    style = AppTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center,
                    fontSize = 50.sp,
                    modifier = Modifier
                        .size(100.dp)
                        .fillMaxSize()
                        .padding(20.dp),
                    color = Color.White
                )
            }
            Text(
                text = text,
                style = AppTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
            )
        }
    }
}

@Preview
@Composable
fun Profile_() {

}