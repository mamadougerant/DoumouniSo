package com.malisoftware.components.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.malisoftware.components.TextDisposition
import com.malisoftware.components.icons.FeedBackIcon
import com.malisoftware.components.icons.HeartIcon
import com.malisoftware.components.icons.SmallLeftIcon
import com.malisoftware.theme.AppTheme
import kotlin.random.Random
/**
 * BusinessContainer is a composable function that creates a container for representing businesses,
 * such as restaurants and markets, with an image, title, subtitle, description, feedback rating,
 * and optional icons for user interaction.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param imageHeight Height of the business image.
 * @param title Title of the business.
 * @param subTitle Subtitle or secondary information about the business.
 * @param deliveryFee Description of the business.
 * @param feedBack Feedback rating of the business.
 * @param imageUrl URL of the image representing the business.
 * @param onClick Lambda function to be executed when the business container is clicked.
 * @param onFavoriteClick Lambda function to handle favorite icon clicks, receives a Boolean indicating the favorite state.
 * @param color Background color of the business container.
 * @param shape Shape of the container, default is [RoundedCornerShape] with a radius of 10.dp.
 *
 * @sample BusinessContainer_
 */
@Composable
fun BusinessContainer(
    modifier: Modifier = Modifier,
    imageHeight: Dp = 150.dp,
    title: String = "Title",
    subTitle: String = "SubTitle",
    deliveryFee: String = "0",
    feedBack: String = "4.4",
    imageUrl: String? = null,
    onClick: () -> Unit = {},
    onFavoriteClick: (Boolean) -> Unit = {},
    color: Color? = null,
    shape: Shape = RoundedCornerShape(10.dp),
    topLeftText: String = "",
    smallBottomText: String = "4",
    isOpen: Boolean = true,
    isFavorite: Boolean = false,
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ImageContainer(
            modifier = Modifier
                .height(imageHeight)
                .fillMaxWidth(),
            onClick = onClick,
            imageUrl = imageUrl,
            color = color,
            shape = shape,
            leftIcon = { if (smallBottomText!="") SmallLeftIcon(text = smallBottomText.toString(), modifier = Modifier.padding(10.dp), textColor = Color.Black) },
            topRightIcon = { HeartIcon(onFavoriteClick,isFavorite) },
            topLeftIcon = { if (topLeftText.isNotEmpty())
                SmallLeftIcon(
                    text = topLeftText,
                    modifier = Modifier.padding(vertical = 10.dp),
                    shape = RoundedCornerShape(bottomEndPercent = 50, topEndPercent = 50, topStartPercent = 0, bottomStartPercent = 0),
                    color = AppTheme.colors.primary
                    )
            },
            enabled = isOpen,
        )
        TextDisposition(
            modifier = Modifier
                .height(80.dp)
                .then(modifier.padding(horizontal = 5.dp)),
            h1 = title,
            h1Style = AppTheme.typography.titleLarge,
            h2 = subTitle,
            h2Style = AppTheme.typography.titleMedium,
            h3 = deliveryFee,
            h3Style = AppTheme.typography.titleSmall,
            rightContent = {
                FeedBackIcon(onClick = if (isOpen) onClick else {{}}, text = feedBack)
            }

        )
    }

}







@Composable
fun TopRightText() {
    
}





@Preview
@Composable
fun BusinessContainer_() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        contentAlignment = Alignment.Center,

        ) {
        BusinessContainer(
            modifier = Modifier
                .width(235.dp)
                .padding(horizontal = 10.dp),
            imageHeight = 150.dp,
        )
    }
}

@Preview
@Composable
fun ManualTestPreview() {
    val listTitle = listOf("qwerty", "asdfgh", "zxcvbn", "poiuyt", "lkjhgf")
    val listDescription = listOf("qweddddrty", "ddddddnnyvgcddd", "zxddddcggggvbn", "poiddddsssssduyt", "dddddddddddddddd")
    val listFeedBack = listOf("1.6", "2.7", "3.8", "4.9", "5.0")

    LazyColumn{
        items(4){i->
            val width = Random.nextInt(100, 500)
            val height = Random.nextInt(100, 500)
            BusinessContainer(
                modifier = Modifier
                    .width(width.dp),
                imageHeight = height.dp,
                title = listTitle[i],
                subTitle = listDescription[i],
                deliveryFee = "1",
                feedBack = listFeedBack[i],
                imageUrl = null,
                onClick = { },
                onFavoriteClick = { },
                color = null,
                shape = RoundedCornerShape(10.dp),
            )
        }
    }

}