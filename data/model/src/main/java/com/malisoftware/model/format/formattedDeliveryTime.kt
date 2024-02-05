package com.malisoftware.model.format

fun formatDeliveryTime(price: Int): String {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault())
    return formatter.format(price) + " " + "min"
}