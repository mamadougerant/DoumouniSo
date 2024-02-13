package com.malisoftware.components.LazyLists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.GridSmallContainer
import com.malisoftware.theme.AppTheme

@Composable
fun GridSmallContainerWithTitle(
    modifier: Modifier = Modifier,
    title: String? = "Categories",
    list: List<String> = List(7) { "Hellovvh" },
    onClick: (String) -> Unit = { }
) {
    if (list.isEmpty()) return
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        if (title != null) {
            Text(
                text = title,
                style = AppTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                color = Color.Black
            )
        }
        GridSmallContainer(
            list = list,
            onClick = onClick
        )
    }

}