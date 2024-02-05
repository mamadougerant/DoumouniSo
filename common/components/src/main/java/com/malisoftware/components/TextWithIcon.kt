package com.malisoftware.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.malisoftware.theme.AppTheme

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    leadingIcon: (@Composable () -> Unit)? = null,
    icon: @Composable () -> Unit,
) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        leadingIcon?.invoke()
        Text(
            text = title,
            style = AppTheme.typography.headlineLarge
        )
        icon()
    }
}

