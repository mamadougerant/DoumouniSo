package com.malisoftware.components.icons

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.malisoftware.theme.AppTheme

@Composable
fun NavigationIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    navIcon: (@Composable () -> Unit)? = null,
    color: Color = Color.LightGray,
    containerColor: Color = AppTheme.colors.onBackground,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(color, containerColor)
    ) {
        if (navIcon != null) navIcon.invoke()
        else Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "")
    }
}