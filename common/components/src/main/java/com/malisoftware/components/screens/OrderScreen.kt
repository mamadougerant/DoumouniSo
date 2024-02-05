package com.malisoftware.components.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.component.scaffold.ItemCustomScaffold
import com.malisoftware.model.Items
import com.malisoftware.model.format.formatPrice
import com.malisoftware.theme.AppTheme
import com.doumounidron.theme.DoumouniDronTheme
import com.malisoftware.components.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderScreenInModalSheet(
    modifier: Modifier = Modifier,
    onBottomBarClick: (Items) -> Unit = {},
    item: Items = Items(),
    onValueChange: (String) -> Unit = {},
    onSheetClose: (Boolean) -> Unit = {},
    bottomContent: (@Composable () -> Unit )? = null,
    extraContent: LazyListScope.() -> Unit = {},

    ) {
    //var isSheetOpen by remember { mutableStateOf(isOpen) }
    val sheetState = rememberModalBottomSheetState(true)
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { onSheetClose(false) },
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f),
        dragHandle = null,

    ) {
        OrderScreen(
            modifier = modifier,
            onNavIconClick = { onSheetClose(false) },
            onBottomBarClick = { onBottomBarClick(it) ; onSheetClose(false) },
            item = item,
            onSpecialOrderValueChange = onValueChange,
            extraContent = extraContent,
            bottomContent = bottomContent
        )
    }

}

@Composable
fun OrderScreen(
    modifier: Modifier = Modifier,
    onNavIconClick: () -> Unit = {},
    onBottomBarClick: (Items) -> Unit = {},
    item: Items = Items(),
    onSpecialOrderValueChange: (String) -> Unit = {},
    bottomContent: (@Composable () -> Unit )? = null,
    extraContent: LazyListScope.() -> Unit = {}
) {

    ItemCustomScaffold(
        modifier = modifier
            .padding(bottom = 105.dp),
        imageUrl = item.imageUrl,
        navIcon = {
            IconButton(onClick = onNavIconClick, modifier = Modifier) {
                Icon(imageVector = Icons.Default.Clear, contentDescription = "")
            }
        },
        text = item.title,
        bottomBar = {
            if (bottomContent == null)
                 OrderBottomBar(
                     onAddClick = { onBottomBarClick(item.copy(quantity = it)) },
                     price = item.price
                 )
        },
    ){
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(if (isSystemInDarkTheme()) AppTheme.colors.background else Color.White),
                shape = RoundedCornerShape(0.dp),

            ) {
                TextDisposition(
                    h1 = item.title,
                    h2 = item.formattedPrice,
                    h3 = item.description,
                    h1Style = AppTheme.typography.headlineLarge,
                    h2Style = AppTheme.typography.titleMedium,
                    h3Style = AppTheme.typography.titleSmall,
                    modifier = Modifier.padding(10.dp),
                ) {}
            }
            Divider()
        }
        extraContent()

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth() ,
                colors = CardDefaults.cardColors(if (isSystemInDarkTheme()) AppTheme.colors.background else Color.White),
                shape = RoundedCornerShape(0.dp),
            ){
                SpecialInstruction(onValueChange = onSpecialOrderValueChange)
            }
        }
    }
}

@Composable
fun OrderBottomBar(
    onAddClick: (Int) -> Unit = {},
    price: Double = 1000.0
) {
    var number by remember{ mutableStateOf(1)}
    BottomAppBar(
        containerColor = Color.LightGray,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row (
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ){
                val color = if (number > 1) Color.White else Color.White.copy(0.5f)
                IconButton(
                    onClick = { if (number > 1) { number-- } },
                    colors = IconButtonDefaults.iconButtonColors(color, Color.Black),
                    modifier = Modifier.size(40.dp)
                ) { Icon(painterResource(id = R.drawable.ic_baseline_minus), contentDescription = "") }
                Text(text = number.toString(), style = AppTheme.typography.titleLarge,color = Color.Black)
                IconButton(
                    onClick = { if (number < 9) { number++ } },
                    colors = IconButtonDefaults.iconButtonColors(Color.White,Color.Black),
                    modifier = Modifier.size(40.dp)
                ) { Icon(Icons.Rounded.Add, contentDescription = "") }
            }
            TextButton(
                onClick = { onAddClick(number) },
                modifier = Modifier
                    .weight(2f)
                    .height(75.dp)
                    .padding(vertical = 10.dp),
                contentPadding = PaddingValues(5.dp),
                colors = ButtonDefaults.buttonColors(if (isSystemInDarkTheme()) Color.White else Color.Black),
                shape = RoundedCornerShape(10.dp)
            ) {
                val text = buildAnnotatedString {
                    withStyle(style = AppTheme.typography.titleLarge.toSpanStyle()) {
                        append("Ajouter - ")
                    }
                    withStyle(style = AppTheme.typography.titleMedium.toSpanStyle()) {
                        append(formatPrice(price * number))
                    }
                }
                Text(
                    text = text,
                    modifier = Modifier.padding( 10.dp),
                    style = AppTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = if (isSystemInDarkTheme()) Color.Black else Color.White

                )
            }
        }
    }
}

@Composable
fun SpecialInstruction(
    onValueChange: (String) -> Unit = {}
) {
    var value by remember { mutableStateOf("instructions") }
    OutlinedTextField(
        value = value,
        onValueChange = { value = it ; onValueChange(it) },
        label = {
            Text(
                text = "Instruction Special",
                style = AppTheme.typography.titleMedium,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(10.dp),
        shape = RoundedCornerShape(10.dp),

    )
}

@Preview
@Composable
fun OrderScreen_() {
    DoumouniDronTheme {
        OrderScreenInModalSheet(
            modifier = Modifier.padding(horizontal = 10.dp),
        )
    }

}