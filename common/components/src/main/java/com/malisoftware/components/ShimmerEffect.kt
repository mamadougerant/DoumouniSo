package com.malisoftware.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color

/**
 * Modifier that applies a shimmer effect to the background color.
 *
 * @param secondModifier The additional modifier to be applied.
 * @param enabled Whether the shimmer effect is enabled or not.
 */
fun Modifier.shimmerEffect(
    secondModifier: Modifier = Modifier,
    enabled: Boolean = true,
    duration: Int = 1000,
    shimmerColors: Color = Color.White
): Modifier = composed {
    val colors = listOf(
        shimmerColors.copy(alpha = 0.7f),
        shimmerColors.copy(alpha = 0.2f),
    )
    val transition = rememberInfiniteTransition(label = "")
    val color = transition.animateColor(
        initialValue = colors[0],
        targetValue = colors[1],
        animationSpec = infiniteRepeatable(
            animation = tween(duration),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    )
    if (enabled) {
        this.then(secondModifier)
            .background(color.value)
    }else this

}