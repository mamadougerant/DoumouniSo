package com.malisoftware.components.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme

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
            .padding(horizontal = 10.dp)
        ,
        containerColor = Color.Unspecified,
        shape = RoundedCornerShape(10.dp),
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
                    .padding(horizontal = 20.dp, vertical = 10.dp),
                textAlign = TextAlign.Center,
                color = Color.Black
            )
            Divider( )

            Text(
                text = "Supprimer",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .padding(vertical = 10.dp)
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
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),

            ) {
            Text(
                text = "Annuler",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                textAlign = TextAlign.Center,
                style = AppTheme.typography.titleMedium
            )
        }
        Spacer(modifier = Modifier
            .height(15.dp)
            .background(Color.Unspecified))

    }
}