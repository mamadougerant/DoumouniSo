package com.malisoftware.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    extraLarge = RoundedCornerShape(5.dp),
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(15.dp),
    large = RoundedCornerShape(20.dp),
)

object CardShapes {

}

object CardSizes{
    val small = 60.dp
    val medium = 100.dp
    val large = 120.dp
    val extraLarge = 130.dp

}
