package com.malisoftware.components.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextWithIcon
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.ButtonSizes
import com.malisoftware.theme.PaddingSizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartItemBottomBar(
    modifier: Modifier = Modifier,
    total: Double,
    deliveryFee: Double = 0.0,
    enabled: Boolean = true,
    onCheckout: () -> Unit = {},
    onUserTerms: () -> Unit = {},
) {

    var show by remember { mutableStateOf(true) }

    Card(
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(0.dp),
        onClick = { show = !show },

    ) {
        AnimatedVisibility(visible = show) {
            TextWithIcon(
                title = "Sous-Total", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PaddingSizes.Dp10, vertical = PaddingSizes.Dp5),
                customStyle = AppTheme.typography.titleMedium
            ) {
                Text(text = formatPrice(total), style = AppTheme.typography.titleMedium)
            }
        }
        AnimatedVisibility(visible = show) {
            TextWithIcon(
                title = "Frais de Livrason", modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = PaddingSizes.Dp10, vertical = PaddingSizes.Dp5),
                customStyle = AppTheme.typography.titleMedium
            ) {
                Text(text = formatPrice(deliveryFee), style = AppTheme.typography.titleMedium)
            }
        }

        TextWithIcon(title = "Total", modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSizes.Dp10, vertical = PaddingSizes.Dp5)) {
            Text(
                text = formatPrice(total + deliveryFee),
                style = AppTheme.typography.titleMedium,
            )
        }
        AnimatedVisibility(visible = show) {
            val text = buildAnnotatedString {
                withStyle(AppTheme.typography.titleSmall.toSpanStyle()) {
                    append("En continuant vous acceptez les ")
                }
                withStyle(
                    AppTheme.typography.titleSmall.copy(textDecoration = TextDecoration.Underline)
                        .toSpanStyle()
                ) {
                    append("conditions de vente,")
                }
                withStyle(AppTheme.typography.titleSmall.toSpanStyle()) {
                    append(" vous acceptez les ")
                }
                withStyle(
                    AppTheme.typography.titleSmall.copy(textDecoration = TextDecoration.Underline)
                        .toSpanStyle()
                ) {
                    append("conditions d'utilisation")
                }
            }
            Text(
                text = text ,
                modifier = Modifier
                    .padding(horizontal = PaddingSizes.Dp10, vertical = PaddingSizes.Dp5)
                    .clickable { onUserTerms() },
                textAlign = TextAlign.Start,
            )
        }

        Button(
            onClick = { onCheckout() },
            colors = ButtonDefaults.buttonColors(
                AppTheme.colors.onBackground,
                AppTheme.colors.background
            ),
            enabled = enabled,
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingSizes.Dp5)
                .padding(vertical = PaddingSizes.Dp5)
                .height(ButtonSizes.large),
            shape = AppTheme.shapes.medium,
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "Commander",
                style = AppTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaddingSizes.Dp5),
                textAlign = TextAlign.Center
            )
        }
    }

}