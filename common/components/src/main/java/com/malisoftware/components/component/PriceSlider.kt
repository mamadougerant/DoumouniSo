package com.malisoftware.components.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.PaddingSizes

@Composable
fun PriceSlider(
    steps: Int = 50,
    onClick: (Float,Float) -> Unit = { _, _ -> },
    textButton: String = "Appliquer"
) {
    RangeSliderWithGraph(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSizes.Dp30)
            .padding(bottom = PaddingSizes.Dp30),
        startValue = 0f,
        endValue = 10000f,
        size = 42,
        steps = steps,
        onValueChangeFinished = { },
        content = { start, end ->
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Min: ${formatPrice(start.toDouble())}")
                    Text(text = "Max: ${formatPrice(end.toDouble())}")
                }
                Button(
                    onClick = { onClick(start, end) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = PaddingSizes.Dp10),
                    shape = AppTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        AppTheme.colors.onBackground
                    )
                ){
                    Text(
                        text = textButton ,
                        modifier = Modifier.padding(PaddingSizes.Dp10),
                        textAlign = TextAlign.Center,
                        color = AppTheme.colors.background,
                        style = AppTheme.typography.titleLarge
                    )
                }
            }
        }
    )
}