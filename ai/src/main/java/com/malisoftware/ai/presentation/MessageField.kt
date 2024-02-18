package com.malisoftware.ai.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme

@Composable
fun BoxScope.MessageField(
    onSendClick: (String)-> Unit = {},
    onMessageChange: (String)-> Unit = {},
    enabled: Boolean = false

) {
    var text by remember { mutableStateOf("") }
    // android:windowSoftInputMode="adjustResize" in <Activity> in AndroidManifest.xml is required for this to work
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Unspecified)
            .padding(
                top = 5.dp,
                bottom = 10.dp,
                start = 0.dp,
                end = 0.dp
            )
            .align(Alignment.BottomCenter)
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onMessageChange(it)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    onSendClick(text)
                    text = ""
                }
            ),
            textStyle = AppTheme.typography.titleMedium,
            modifier = Modifier
                .weight(7f)
                .heightIn(min = 30.dp, max = 100.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(15.dp)
                ),
            decorationBox = { innerTextField ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp, horizontal = 10.dp),
                ){
                    Box(
                        modifier = Modifier
                            .weight(1f)
                    ) {
                        innerTextField()
                    }

                    IconButton(
                        onClick = {
                            onSendClick(text)
                            text = ""
                        },
                        modifier = Modifier.size(25.dp),
                        enabled = enabled && text.isNotEmpty() && text.isNotBlank()
                    )  {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "",
                            modifier = Modifier
                                .rotate(90f)
                                .shadow(1.dp,)
                        )
                    }
                }

            }
        )

    }
}

@Preview
@Composable
fun MessageField_() {
    Box {
        MessageField()
    }
}
