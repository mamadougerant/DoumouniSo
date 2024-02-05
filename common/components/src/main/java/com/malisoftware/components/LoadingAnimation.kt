package com.malisoftware.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LoadingAnimation(
    modifier: Modifier = Modifier,
    boxSize: Dp = 15.dp,
    color: Color = Color.Black
) {
    val items = listOf(
        remember{ Animatable(initialValue = 0f) },
        remember{ Animatable(initialValue = 0f) },
        remember{ Animatable(initialValue = 0f) }
    )

    items.forEachIndexed { index, animatable -> Animation(animatable, index) }

    @Composable
    fun Modifier.Circle(
        item: Float,
        distance: Float,
        size: Dp = boxSize
    ): Modifier {
        return size(size)
        .graphicsLayer { this.translationY = -item * distance }
        .background(
            color = color,
            shape = CircleShape
        )
    }

    val distance = with(LocalDensity.current) { 10.dp.toPx() }

    Box (
        modifier = modifier.background(color = Color.Transparent),
        contentAlignment = Alignment.Center
    ){
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            items.map { it.value }.forEach { item ->
                Box(modifier = Modifier.Circle(item = item, distance = distance) )
            }
        }
    }

}
@Composable
fun Animation(
    animatable: Animatable<Float, AnimationVector1D>,
    index: Int
) {
    val animation =  keyframes {
        durationMillis = 1200
        0.0f at 0 using LinearOutSlowInEasing
        1.0f at 300 using LinearOutSlowInEasing
        1.0f at 600 using LinearOutSlowInEasing
        0.0f at 1200 using LinearOutSlowInEasing
    }
    LaunchedEffect(key1 = animatable ){
        delay(index * 100L)
        animatable.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = animation,
                repeatMode = RepeatMode.Restart
            )
        )

    }
}

@Preview
@Composable
fun _LoadingAnimation() {
    LoadingAnimation(
        modifier = Modifier.fillMaxSize(),
        boxSize = 15.dp,
        color = MaterialTheme.colorScheme.primary
    )

}