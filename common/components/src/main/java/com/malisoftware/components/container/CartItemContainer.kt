package com.malisoftware.components.container

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.malisoftware.model.BusinessData
import com.malisoftware.theme.AppTheme


@Composable
fun CartItemContainer(
    modifier: Modifier = Modifier,
    business: BusinessData = BusinessData(),
    onClear: () -> Unit = {},
    onBusinessForward: (BusinessData) -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    OutlinedCard (
        modifier = modifier
            .fillMaxWidth()
    ) {
        SmallBusinessContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            imageUrl = business.imageUrl,
            title = business.title,
            subtitle = business.category,
        ) { IconButton(onClick = onClear) {
            Icon(Icons.Rounded.Clear, contentDescription = "")
        } }
        Button(
            onClick = onConfirm,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(horizontal = 5.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(AppTheme.colors.onBackground),
            contentPadding = PaddingValues(0.dp)
        ) {
            val text = buildAnnotatedString {
                withStyle(AppTheme.typography.titleMedium.toSpanStyle()) {
                    append("Voir le panier")
                }
            }
            Text(
                text = text,
                style = AppTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 15.dp, horizontal = 10.dp),
                color = AppTheme.colors.background
            )
        }
        Button(
            onClick = { onBusinessForward.invoke(business) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .padding(horizontal = 5.dp)
                .height(50.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color.LightGray.copy(0.5f) else Color.LightGray),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Voir l'etablissement",
                style = AppTheme.typography.titleMedium,
                modifier = Modifier
                    .fillMaxHeight()
                    .align(Alignment.CenterVertically)
                    .padding(15.dp),
                color = AppTheme.colors.background
            )
        }


    }
}