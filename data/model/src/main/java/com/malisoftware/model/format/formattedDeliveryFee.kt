package com.malisoftware.model.format

fun formatDeliveryFee(price: Double): String {
    val formatter = java.text.NumberFormat.getNumberInstance(java.util.Locale.getDefault())
    return if (price == 0.0) "Livaraison Gratuite"
    else "Frais de Livaraison" + " " + formatter.format(price) + " " + "FCFA"
}