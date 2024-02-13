package com.malisoftware.components.container


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items
import com.malisoftware.theme.AppTheme

fun LazyListScope.ItemHeader(
    shop: BusinessData?,
    itemsInPromotion: List<Items>,
    onClick: (Items) -> Unit,
) {
    item {
        val subCard1 = if (!shop?.isOpen!!){
            "Ce Etablissement est Fermé"
        } else {
            "Temps de livraison\n" + shop.formattedDeliveryTime
        }
        val promotion = buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Red)) {
                append(if (shop.promotion != "") "\n"+"* "+ shop.promotion +" *" else "")
            }
        }
        Card (
            colors = CardDefaults.cardColors(AppTheme.colors.background),
            shape = RoundedCornerShape(0.dp),
        ) {
            BusinessInfo(
                modifier = Modifier
                    .fillMaxWidth(),
                title = shop.title,
                subtitle = shop.feedback + " ⭐ - " + shop.formattedMinPrice + promotion.toString(),
                subInCard1 = subCard1,
                subInCard2 = shop.formattedDeliveryFee,
                subInCard1Color = if (!shop.isOpen) Color.Red else Color.Unspecified,
                subInCard2Color = if (shop.deliveryFee == 0.0 && shop.isOpen ) Color.Green else Color.Unspecified,

            )
            ItemWithTitleBar(
                data = itemsInPromotion,
                onClick = {
                    onClick(it)
                }
            )
        }
    }
}