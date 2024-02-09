package com.malisoftware.components.LazyLists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.BusinessContainer
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.model.BusinessData

@Composable
fun RowBusinessList(
    modifier: Modifier = Modifier,
    title: String = "Categories",
    businessData: List<BusinessData>,
    onClick: (BusinessData) -> Unit = {},
    color: Color? = null,
    trailingContent: @Composable () -> Unit = {},
    favoriteBusiness: List<BusinessData>,
    onFavoriteClick: (BusinessData, Boolean) -> Unit,
) {
    RowListContainer(
        modifier = modifier,
        title = title,
        trailingContent = trailingContent,
    ){
        items(businessData.size) {
            val hapticFeedback: HapticFeedback = LocalHapticFeedback.current

            BusinessContainer(
                modifier = Modifier
                    .width(235.dp),
                imageHeight = 150.dp,
                title = businessData[it].title ,
                subTitle = businessData[it].category + " - " + businessData[it].formattedMinPrice,
                deliveryFee = businessData[it].formattedDeliveryFee,
                feedBack = businessData[it].feedback,
                imageUrl = businessData[it].imageUrl,
                onClick = {
                    onClick(businessData[it])
                    hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                },
                onFavoriteClick = { fav-> onFavoriteClick(businessData[it],fav) },
                isFavorite = favoriteBusiness.contains(businessData[it]),
                color = color,
                shape = RoundedCornerShape(10.dp),
                topLeftText = businessData[it].promotion,
                smallBottomText = businessData[it].formattedDeliveryTime,
                isOpen = businessData[it].isOpen,
            )
        }
    }
}

fun LazyListScope.ColumnBusinessList(
    modifier: Modifier = Modifier,
    businessData: List<BusinessData>,
    favoriteBusiness: List<BusinessData> = emptyList(),
    title: @Composable (() -> Unit)? = null,
    onClick: (BusinessData) -> Unit = {},
    onFavoriteClick: (BusinessData,Boolean) -> Unit = { businessData: BusinessData, b: Boolean -> },
    color: Color? = null,
) {

    if (businessData.isEmpty()) {
        return
    }

    if (title != null) {
        item { title() }
    }
    items(businessData.size) {
        val hapticFeedback: HapticFeedback = LocalHapticFeedback.current

        BusinessContainer(
            modifier = modifier
                .fillMaxWidth(),
            imageHeight = 180.dp,
            title = businessData[it].title ,
            subTitle = businessData[it].category + " - " + businessData[it].formattedMinPrice,
            deliveryFee = businessData[it].formattedDeliveryFee,
            feedBack = businessData[it].feedback,
            imageUrl = businessData[it].imageUrl,
            onClick = {
                onClick(businessData[it]);
                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            },
            onFavoriteClick = { fav-> onFavoriteClick(businessData[it],fav) },
            color = color,
            shape = RoundedCornerShape(10.dp),
            topLeftText = businessData[it].promotion,
            smallBottomText = businessData[it].formattedDeliveryTime,
            isOpen = businessData[it].isOpen,
            isFavorite = favoriteBusiness.contains(businessData[it]),
        )
    }

}



@Preview
@Composable
fun BusinessList_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(10.dp),
        contentAlignment = Alignment.Center,
    ) {

    }
}