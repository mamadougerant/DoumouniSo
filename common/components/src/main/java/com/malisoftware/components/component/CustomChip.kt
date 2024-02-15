package com.malisoftware.components.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * CustomChip is a composable function that creates a customizable chip UI element.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param selected Indicates whether the chip is in a selected state.
 * @param onClick Lambda function to be executed when the chip is clicked.
 * @param label Lambda function defining the content of the chip.
 * @param trailingIcon Optional lambda function defining the content of the trailing icon.
 * @param color Background color of the chip, default is [Color.LightGray].
 *
 * @see BadgeCustomChip
 * @sample BadgeCustomChip_
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomChip(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    label: @Composable () -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null,
    color: Color? = null,
) {
    val hapticFeedback = androidx.compose.ui.platform.LocalHapticFeedback.current
    InputChip(
        modifier = modifier,
        selected = selected,
        onClick = {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick()
        },
        label = label,
        shape = CircleShape,
        trailingIcon = trailingIcon,
        colors = InputChipDefaults.inputChipColors(
            containerColor = color?.copy(alpha = 0.5f) ?: Color.LightGray,
            selectedContainerColor = color ?: Color.Black,
            disabledContainerColor = Color.LightGray,
            labelColor = Color.Black,
            selectedLabelColor = Color.White,
            trailingIconColor = Color.Black,
            selectedTrailingIconColor = Color.White,
        ),
        border = InputChipDefaults.inputChipBorder(
            borderColor= Color.Unspecified,
            selectedBorderColor=Color.Black,
            selectedBorderWidth = 1.dp,
            enabled = true,
            selected = selected
        ),
        elevation = InputChipDefaults.inputChipElevation(
            pressedElevation = 25.dp,
            draggedElevation = 25.dp,
            elevation = if (selected) 25.dp else 0.dp
        )

    )
}

/**
 * BadgeCustomChip is a composable function that combines a badged chip with a customizable chip UI element.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param selected Indicates whether the chip is in a selected state.
 * @param onClick Lambda function to be executed when the chip is clicked.
 * @param label Lambda function defining the content of the chip.
 * @param text Text to be displayed as the badge content.
 * @param color Background color of the chip, default is [Color.LightGray].
 *
 * @see CustomChip
 * @sample CustomChip_
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BadgeCustomChip(
    modifier: Modifier = Modifier,
    selected: Boolean = false,
    onClick: () -> Unit = {},
    label: @Composable () -> Unit = {},
    text: String = "2",
    color: Color? = Color.LightGray,
) {
    BadgedBox(badge = {
        Badge (modifier = Modifier.offset(x = (-8).dp, y = (8).dp),) {
            Text(text = text, style = MaterialTheme.typography.bodySmall)
        }
    }) {
        CustomChip(
            modifier = modifier,
            selected = selected,
            onClick = onClick,
            label = label,
            color = color
        )
    }
}

@Preview
@Composable
fun CustomChip_() {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        BadgeCustomChip(
            modifier = Modifier
                .height(35.dp),
            label = {
                Text(text = "hello", style = MaterialTheme.typography.titleMedium)
            }
        )
        CustomChip(
            modifier = Modifier
                .height(35.dp)
                .align(Alignment.BottomCenter),
            label = {
                Text(text = "Hello", style = MaterialTheme.typography.titleMedium)
            },
            trailingIcon = {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.width(24.dp)
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, "")
                }
            },
            selected = true
        )
    }
}

@Preview
@Composable
fun BadgeCustomChip_() {
    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ){
        BadgeCustomChip(
            modifier = Modifier
                .height(35.dp),
            label = {
                Text(text = "hello", style = MaterialTheme.typography.titleMedium)
            }
        )
    }
}