package com.malisoftware.orders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import com.malisoftware.components.TextWithIcon
import com.malisoftware.components.container.OrderInfoCard
import com.malisoftware.theme.AppTheme

@Composable
fun OrderInfoScreen() {

}

@Composable
fun Container() {
    Column {
        OrderInfoCard(
            text = "Your order is on the way",
            subText = "Your order is on the way",
            info = "votre cammande sera livrer par l etablissement " +
                    "le suivi de la commande est limeter. " +
                    "le contact de l etablissement apparaitra pour assistance apres le delai de livraison",
        )
        TextWithIcon(
            title = "Details",
            customStyle = AppTheme.typography.titleMedium,
        ) {}
        TextWithIcon(title = "Details") {}
    }
}