package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.malisoftware.components.icons.OutlinedButtonWithText
import com.malisoftware.theme.CardSizes.searchButtonsLazyLayoutHeight
import com.malisoftware.theme.PaddingSizes


@Composable
fun GridSmallContainer(
    list: List<String> = List(7) { "Hello" },
    onClick: (String) -> Unit = {}
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.spacedBy(PaddingSizes.Dp10),
        modifier = Modifier
            .fillMaxWidth()
            .height(searchButtonsLazyLayoutHeight),
        verticalArrangement = Arrangement.spacedBy(PaddingSizes.Dp10)

    ){
        items(list.size){
            val item = list[it]
            OutlinedButtonWithText(
                text = item,
                onClick = { onClick(item) }
            )
        }
    }
}