package com.malisoftware.components

fun formatPrice(price: Double): String {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault())
    return formatter.format(price)
}