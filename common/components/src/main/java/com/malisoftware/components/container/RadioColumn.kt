package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.PaddingSizes

@Composable
fun RadioColumn(
    modifier: Modifier = Modifier,
    options: List<Pair<String,(@Composable () -> Unit)?>> = List(3) { "Option $it" to null },
    getSelected: (String) -> Unit = {},
    titleContent: @Composable () -> Unit = {},
) {
    var selectedOption by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(PaddingSizes.Dp10)
    ){
        titleContent()
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                option.second?.invoke()
                Text(text = option.first, style = AppTheme.typography.titleMedium)
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black,
                        unselectedColor = Color.Black,
                    ),
                    selected = option.first == selectedOption,
                    onClick = { selectedOption = option.first; getSelected(option.first) },
                )
            }
        }
    }
}

