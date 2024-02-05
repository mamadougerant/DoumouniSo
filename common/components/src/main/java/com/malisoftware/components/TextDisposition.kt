package com.malisoftware.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextDisposition(
    modifier: Modifier = Modifier,
    h1: String? = null,
    h1Style: TextStyle? = null,
    h2: String? = null,
    h2Style: TextStyle? = null,
    h3: String? = null,
    h3Style: TextStyle? = null,
    rightContent: @Composable () -> Unit = {},

) {
    Row (
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextColumn(
            modifier = Modifier.weight(1f),
            h1 = h1,
            h1Style = h1Style,
            h2 = h2,
            h2Style = h2Style,
            h3 = h3,
            h3Style = h3Style,
        )
        rightContent()
    }

}

@Composable
private fun TextColumn(
    modifier: Modifier = Modifier,
    h1: String? = "H1",
    h1Style: TextStyle? = null,
    h2: String? = "H2",
    h2Style: TextStyle? = null,
    h3: String? = "H3",
    h3Style: TextStyle? = null,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = horizontalAlignment,
    ){
        if (h1 != null) {
            Text(
                text = h1.replaceFirstChar { it.titlecaseChar() },
                style = h1Style ?: TextStyle(),
                maxLines = 1,
                //modifier = Modifier.fillMaxWidth(),
            )
        }
        if (h2 != null) {
            Text(
                text = h2,
                style = h2Style ?: TextStyle(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        if (h3 != null) {
            Text(
                text = h3,
                style = h3Style ?: TextStyle(),
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
fun TextDisposition_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

        ) {
        TextDisposition(h1="Hi")
    }
}