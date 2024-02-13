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

@Composable
fun PriceSliderWithText(
    data: List<String> = listOf("1","2","3","4",),
    onClick: (Float,Float) -> Unit = { _, _ -> }
) {
    RangeSliderWithData(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
            .padding(bottom = 30.dp),
        data = data,
        dataModifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 5.dp),
        content = { start, end ->
            Button(
                onClick = { onClick(start, end) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    AppTheme.colors.onBackground
                )
            ){
                Text(
                    text = "Appliquer" ,
                    modifier = Modifier.padding(10.dp),
                    textAlign = TextAlign.Center,
                    color = AppTheme.colors.background,
                    style = AppTheme.typography.titleLarge
                )
            }
        }

    )
}