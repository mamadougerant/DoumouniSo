package com.malisoftware.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val appShapes = Shapes(
    extraLarge = RoundedCornerShape(25.dp),
    small = RoundedCornerShape(5.dp),
    medium = RoundedCornerShape(10.dp),
    large = RoundedCornerShape(20.dp),
    extraSmall = RoundedCornerShape(0.dp),
)

object CardShapes {

}

object CardSizes{
    val storeHeight = 136.dp
    val roundedBusinessPic = 70.dp
    val promotionBusinessItemBarHeight = 50.dp
    val promotionBusinessItemWidth = 150.dp
    val shopItemHeight = 130.dp
    val shopItemWidth = 115.dp
    val plusIconHeight = 35.dp
    val restaurantItemHeight = 100.dp
    val restaurantItemWidth = 100.dp
    val smallRestaurantItemHeight = 70.dp
    val smallRestaurantItemWidth = 70.dp
    val searchButtonsLazyLayoutHeight = 100.dp
    val categorySize = 90.dp
    val buttonHeight = 50.dp
    val restaurantDeliveryTimeCard = 50.dp // card in which delivery time else fee are shown
    val orderInfoCard = 250.dp
}

object PaddingSizes{
    val paddingO = 0.dp
    val tiny = 1.dp
    val small = 5.dp
    val medium = 10.dp
    val large = 15.dp // also for horizontalArrangement spaceBy
    val extraLarge = 20.dp
}

object HorizontalDividerSizes{
    val tiny = 1.dp
    val small = 2.dp
    val medium = 5.dp
    val large = 8.dp
}

object ButtonSizes {
    val small = 30.dp
    val medium = 40.dp
    val large = 50.dp
    val extraLarge = 60.dp
}

object ImageSizes {
    val small = 50.dp
    val medium = 100.dp
    val large = 150.dp
    val extraLarge = 200.dp
}
