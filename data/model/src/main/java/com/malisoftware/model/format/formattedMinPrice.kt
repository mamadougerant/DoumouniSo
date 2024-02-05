package com.malisoftware.model.format

fun formatMinPrice(price: Double): String {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault())
    return "min." + " " + formatter.format(price) + " " + "FCFA"
}