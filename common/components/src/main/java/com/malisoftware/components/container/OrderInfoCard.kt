package com.malisoftware.components.container

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.PaddingSizes
import com.malisoftware.components.R
import com.malisoftware.components.TextWithIcon
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.CardSizes.orderInfoCard
import com.malisoftware.theme.ImageSizes

@Preview
@Composable
fun OrderInfoCard(
    text: String = "Your order is on the way",
    subText: String = "Your order is on the way",
    info: String = "votre cammande sera livrer par l etablissement " +
            "le suivi de la commande est limeter. " +
            "le contact de l etablissement apparaitra pour assistance apres le delai de livraison",
) {
    Box {
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .height(orderInfoCard)
                .padding(PaddingSizes.Dp20)
                .padding(top = PaddingSizes.Dp15),
            elevation = CardDefaults.cardElevation(10.dp)
        ) {
            Text(
                text = text,
                style = AppTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(horizontal = PaddingSizes.Dp15)
                    .padding(top = PaddingSizes.Dp15)
            )
            Text(
                text = subText,
                style = AppTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(PaddingSizes.Dp15)
            )
            HorizontalDivider()
            TextWithIcon(
                title = info,
                customStyle = AppTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(PaddingSizes.Dp15)
            ) {}
        }
        Image(
            painter = painterResource(id = R.drawable.salad),
            null,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(ImageSizes.medium)

        )
    }
}