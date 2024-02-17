package com.malisoftware.ai.presentation

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChatContainer(
    text: String ,
    isAssistant: Boolean = false
) {
    @Composable
    fun User() {
        Card(
            modifier = Modifier
                .height(34.dp)
                .width(34.dp),
            shape = CircleShape,
            colors = CardDefaults.cardColors(Color.Unspecified),
            border = BorderStroke(1.dp, Color.Black)
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp)
            )
        }
    }

    @Composable
    fun TextContainer(
        modifier: Modifier = Modifier,
        shape: Shape = RoundedCornerShape(20.dp),
        color: Color = MaterialTheme.colorScheme.secondary
    ) {
        fun Modifier.anim (): Modifier {
            return animateContentSize(
                animationSpec = tween(
                    durationMillis = 600,
                    easing = FastOutSlowInEasing
                )
            )
        }
        Card (
            modifier = modifier,
            shape = shape,
            colors = CardDefaults.cardColors(color),
        ){
            SelectionContainer {
                Text(
                    text = text,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp).anim(),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                )
            }
        }
    }
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Unspecified)
            .padding(vertical = 5.dp),
        horizontalArrangement = if(!isAssistant) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Top
    ){
        if (!isAssistant){
            TextContainer(
                modifier= Modifier
                    .widthIn(max = Dp.Infinity)
                    .padding(start = 40.dp, end = 0.dp),
                color = MaterialTheme.colorScheme.primary,
            )
        }else {
            User()
            TextContainer(
                modifier= Modifier
                    .padding(start = 10.dp, end = 40.dp)
                    .weight(7f, fill = false),
            )

        }
    }
}

@Preview
@Composable
fun ChatContainer_() {
    ChatContainer(
        text = "Hello World",
        isAssistant = true
    )
}