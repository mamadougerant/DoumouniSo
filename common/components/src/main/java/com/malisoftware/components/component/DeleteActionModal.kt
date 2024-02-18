package com.malisoftware.components.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.ButtonSizes
import com.malisoftware.theme.PaddingSizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteActionModal(
    onDismissRequest: () -> Unit = {},
    text: String = "Voulez-vous vraiment supprimer ce panier?",
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissRequest() },
        dragHandle = {  },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSizes.Dp10)
        ,
        containerColor = Color.Unspecified,
        shape = AppTheme.shapes.medium,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Text(
                text = text ,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PaddingSizes.Dp20, vertical = PaddingSizes.Dp10),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            HorizontalDivider()

            Text(
                text = "Supprimer",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingSizes.Dp10)
                    .padding(vertical = PaddingSizes.Dp10)
                    .clickable { onConfirm() },
                color = Color.Red,
                textAlign = TextAlign.Center,
                style = AppTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier
            .height(10.dp)
            .background(Color.Unspecified))
        Button(
            onClick = {
               onCancel()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(ButtonSizes.large),
            shape = AppTheme.shapes.medium,

            ) {
            Text(
                text = "Annuler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingSizes.Dp10),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier
            .height(15.dp)
            .background(Color.Unspecified))

    }
}