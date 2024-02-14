package com.malisoftware.components.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.R
import com.malisoftware.theme.AppTheme

@Composable
fun EmptyCard(
    modifier: Modifier = Modifier,
    text: String = "Panier vide",
    actionText: String = "Ajouter des articles",
    action: () -> Unit = {}
) {
    Box (
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.empty_no_drafts) ,
               null,
                modifier = Modifier
                    .width(200.dp)
                    .height(200.dp)
            )
            Text(text = text, style = AppTheme.typography.titleLarge)
            Button(
                onClick = action,
                modifier = Modifier.width(200.dp),
                contentPadding = PaddingValues(5.dp),
                colors = ButtonDefaults.buttonColors( AppTheme.colors.onBackground),
            ) {
                Text(
                    text = actionText,
                    style = AppTheme.typography.titleMedium,
                    color = AppTheme.colors.background
                )
            }
        }
    }
}

@Preview
@Composable
fun EmptyCard_() {
    EmptyCard()
}