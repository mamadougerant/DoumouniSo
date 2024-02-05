package com.malisoftware.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.isFinished
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.malisoftware.theme.AppTheme
import kotlinx.coroutines.delay

@Composable
fun OrderCompletedScreen(
    text1: String = "Merci de votre confiance",
    text2: String = "Nous vous tenons au courant de l'avancement de votre commande",
    onFinished: (Boolean) -> Unit = {}

) {
    val offsetY = remember{ Animatable(initialValue = -4f) }
    
    LaunchedEffect(key1 = offsetY){
        val result = offsetY.animateTo(
            targetValue = 0f,
            animationSpec = tween(durationMillis = 1000, easing = LinearEasing),
        )
        delay(2000)
        onFinished(result.endState.isFinished)
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center,

    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(20.dp)
        ){
            OutlinedCard(
                modifier = Modifier
                    .size(100.dp)
                    .offset(y = offsetY.value * 100.dp),
                shape = CircleShape,
                colors = CardDefaults.cardColors(Color.Unspecified),
                border = BorderStroke(4.dp, Color.Green)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .padding(20.dp)
                        .size(100.dp)
                )
            }

            Text(
                text = text1,
                style = AppTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                color = Color.Green
            )
            Text(
                text = text2,
                style = AppTheme.typography.titleMedium.copy(fontWeight = FontWeight.Normal),
                color = Color.Green,
                textAlign = TextAlign.Center
            )
        }
    }
}
