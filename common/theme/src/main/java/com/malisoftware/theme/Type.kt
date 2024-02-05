package com.malisoftware.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val montSerrat = FontFamily(
    Font(R.font.montserrat, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.montserrat_medium, FontWeight.W500, FontStyle.Normal),
    Font(R.font.montserrat_semibold, FontWeight.W600, FontStyle.Normal),
    Font(R.font.montserrat_bold, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.montserrat_black, FontWeight.Black, FontStyle.Normal),
    Font(R.font.montserrat_extrabold, FontWeight.ExtraBold, FontStyle.Normal),
    Font(R.font.montserrat_light, FontWeight.Light, FontStyle.Normal),
)

// Set of Material typography styles to start with

val CustomTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = montSerrat ,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.sp,
        fontFamily = montSerrat,
    ),
    titleLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        fontFamily = montSerrat,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp,
        fontFamily = montSerrat,

    ),
    titleSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        fontFamily = montSerrat,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        fontFamily = montSerrat,
    )
    // Other default text styles to override
)