package com.malisoftware.components.component

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
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.PaddingSizes

@Composable
fun PriceSliderWithText(
    data: List<String> = listOf("1","2","3","4",),
    onClick: (Float,Float) -> Unit = { _, _ -> }
) {
    RangeSliderWithData(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSizes.Dp30)
            .padding(bottom = PaddingSizes.Dp30),
        data = data,
        dataModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSizes.Dp5),
        content = { start, end ->
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
                    text = "Appliquer" ,
                    modifier = Modifier.padding(PaddingSizes.Dp10),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.background,
                    style = AppTheme.typography.titleLarge
                )
            }
        }

    )
}