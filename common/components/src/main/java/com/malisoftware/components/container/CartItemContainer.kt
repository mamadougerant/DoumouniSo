package com.malisoftware.components.container

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.malisoftware.model.BusinessData
import com.malisoftware.theme.AppTheme
import com.malisoftware.theme.CardSizes.buttonHeight
import com.malisoftware.theme.PaddingSizes


@Composable
fun CartItemContainer(
    modifier: Modifier = Modifier,
    business: BusinessData = BusinessData(),
    onClear: () -> Unit = {},
    onBusinessForward: (BusinessData) -> Unit = {},
    onConfirm: () -> Unit = {},
) {
    OutlinedCard (
        modifier = modifier
            .fillMaxWidth()
    ) {
        SmallBusinessContainer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaddingSizes.medium),
            imageUrl = business.imageUrl,
            title = business.title,
            subtitle = business.category,
        ) { IconButton(onClick = onClear) {
            Icon(Icons.Rounded.Clear, contentDescription = "")
        } }

        CartButton(
            onClick = onConfirm,
            text = "Voir le panier"
        )
        CartButton(
            onClick = { onBusinessForward.invoke(business) },
            text = "Voir l'etablissement",
            color = Color.LightGray
        )

    }
}

@Composable
fun CartButton(
    onClick: () -> Unit = {},
    text: String = "Voir le panier",
    color: Color = AppTheme.colors.onBackground
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaddingSizes.small)
            .padding(horizontal = PaddingSizes.small)
            .height(buttonHeight),
        shape = AppTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(color),
        contentPadding = PaddingValues(0.dp)
    ) {
        Text(
            text = text,
            style = AppTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterVertically)
                .padding(vertical = PaddingSizes.large, horizontal = PaddingSizes.medium),
            color = AppTheme.colors.background
        )
    }
}