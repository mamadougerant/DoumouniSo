package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioColumn(
    modifier: Modifier = Modifier,
    options: List<String> = List(3) { "Option $it" },
    getSelected: (String) -> Unit = {},
    titleContent: @Composable () -> Unit = {},
    leftContent: @Composable () -> Unit = {},
) {
    var selectedOption by remember { mutableStateOf("") }

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        titleContent()
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                leftContent()
                RadioButton(
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Color.Black,
                        unselectedColor = Color.Black,
                    ),
                    selected = option == selectedOption,
                    onClick = { selectedOption = option; getSelected(option) },
                )
            }
        }
    }
}

