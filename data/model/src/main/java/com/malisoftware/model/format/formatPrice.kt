package com.malisoftware.model.format

fun formatPrice(price: Double): String {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault())
    return formatter.format(price) + " FCFA"
}