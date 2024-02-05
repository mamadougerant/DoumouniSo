package com.malisoftware.components.LazyLists

import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.component.CustomChip
import com.malisoftware.components.container.RowListContainer
import com.malisoftware.theme.AppTheme


@Composable
fun ChipList(
    modifier: Modifier = Modifier,
    chips: List<Pair<String,(@Composable () -> Unit)?>>,
    onClick: (String) -> Unit = {},
    selected: List<String> = emptyList(),
) {
    RowListContainer(
        modifier = modifier,
        title = null
    ){
        items(chips.size) {
            CustomChip(
                modifier = Modifier,
                onClick = { onClick.invoke(chips[it].first) },
                label = {
                    Text(
                        text = chips[it].first,
                        style = AppTheme.typography.titleMedium,
                        modifier = Modifier.offset(y= (-0).dp),
                    )
                },
                trailingIcon = chips[it].second,
                selected = chips[it].first in selected
            )
        }
    }
}

@Preview
@Composable
fun ChipList_() {
    val list = List(30){ "example" to null }
    ChipList(
        modifier = Modifier,
        chips = list,
        onClick = {},
    )
}
