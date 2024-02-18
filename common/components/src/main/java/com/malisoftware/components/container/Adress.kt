package com.malisoftware.components.container

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.icons.NavigationIcon
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.ButtonSizes
import com.malisoftware.theme.DoumouniDronTheme
import com.malisoftware.theme.PaddingSizes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressModal(
    onStaClose: (Boolean) -> Unit = {},
    onClick: () -> Unit = {}
) {
    val state = rememberModalBottomSheetState(true)
    ModalBottomSheet(
        onDismissRequest = { onStaClose(false) },
        sheetState = state,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.9f),
        dragHandle = {},
    ) {
        Scaffold (
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Address", style = AppTheme.typography.titleLarge) },
                    navigationIcon = {
                        NavigationIcon(
                            modifier = Modifier,
                            onClick = { onStaClose(false) },
                            navIcon = { Icon(Icons.Default.Clear , contentDescription =null )}
                        )
                    }
                )
            },
            bottomBar = {
                NormalButton(
                    onClick = onClick,
                    text = "Enregistrer"
                )
            }
        ){
            Address(
                modifier = Modifier
                    .padding(it)
            )
        }
    }
}

@Composable
fun NormalButton(
    onClick: () -> Unit = {},
    text: String = "Enregistrer"
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingSizes.Dp10)
            .padding(bottom = PaddingSizes.Dp10)
            .height(ButtonSizes.large)
        ,
        shape = AppTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black,
            contentColor = Color.White
        )
    ) {
        Text(text = text, style = AppTheme.typography.titleLarge)
    }
}



@Composable
fun Address(
    modifier: Modifier = Modifier,
    title: String = "Address",
    addressLine1: String = "Address1",
    addressLine2: String = "Address2",
) {
    LazyColumn (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp) ,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(Color.LightGray)
            ) {
            }
        }
        item {
           // Text(text = title, style = AppTheme.typography.titleLarge)
            AddressIcon(addressLine1 = addressLine1, addressLine2 = addressLine2)
            TwoTextField(
                text1 = "Ville",
                text2 = "Quartier",
                onAddress1Change = {},
                onAddress2Change = {},
            )
            TwoTextField(
                text1 = "Rue",
                onAddress1Change = {},
            )
            TwoTextField(
                text1 = "Code postal",
                text2 = "Numero de maison",
                onAddress1Change = {},
                onAddress2Change = {},
            )
            TwoTextField(
                text1 = "Description",
                onAddress1Change = {},
            )
            TwoTextField(
                text1 = "Description supplémentaire",
                onAddress1Change = {},
            )
            TwoTextField(
                text1 = "Numero de telephone",
                onAddress1Change = {},
            )
        }

    }
}

@Composable
fun AddressIcon(
    addressLine1: String = "Address1",
    addressLine2: String? = null,
    icon: (@Composable () -> Unit)? = null,
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null)
            icon()
        else {
            IconButton(onClick = { }, modifier = Modifier) {
                Icon(imageVector = Icons.Default.LocationOn, contentDescription = "")
            }
        }
        TextDisposition (
            h1 = addressLine1,
            h1Style = AppTheme.typography.titleMedium,
            h2 = addressLine2,
            h2Style = AppTheme.typography.titleSmall,
        ) {}
    }
}

@Composable
fun TwoTextField(
    text1: String = "",
    text2: String? = null,
    onAddress1Change: (String) -> Unit,
    onAddress2Change: (String) -> Unit = {},
) {
    var address1 by rememberSaveable { mutableStateOf("") }
    var address2 by rememberSaveable { mutableStateOf("") }
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            value = address1,
            onValueChange = { address1 = it; onAddress1Change(it) },
            label = { Text(text1, style = AppTheme.typography.titleSmall, ) },
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 5.dp),
            shape = RoundedCornerShape(10.dp),
            maxLines = 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified,
                focusedContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Black,
                cursorColor = Color.Black,
            )
        )
        if (text2 != null)
            TextField(
                value = address2,
                onValueChange = { address2 = it; onAddress2Change(it) },
                label = { Text(text2, style = AppTheme.typography.titleSmall, ) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp),
                maxLines = 1,
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Unspecified,
                    unfocusedIndicatorColor = Color.Unspecified,
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                    cursorColor = Color.Black,

                )
            )
    }
}

@Preview
@Composable
fun Address_() {
    DoumouniDronTheme {
        Address()
    }
}