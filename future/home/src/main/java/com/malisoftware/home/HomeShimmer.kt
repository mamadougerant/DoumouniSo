package com.future.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.components.container.ImageContainer
import com.malisoftware.components.container.StoreContainer
import com.malisoftware.components.shimmerEffect
import com.malisoftware.theme.AppTheme

@Composable
fun HomeShimmer(
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start
    ){
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(15.dp),
        ){
            repeat(2) {
                Spacer(
                    modifier = Modifier
                        .height(126.dp)
                        .weight(1f)
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .shimmerEffect(
                            secondModifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                )
            }

        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            userScrollEnabled = false
        ){
            items(4) {
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .size(90.dp)
                            .background(Color.LightGray, RoundedCornerShape(10.dp))
                            .shimmerEffect(
                                secondModifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                            )
                    )
                    Spacer(modifier = Modifier
                        .height(20.dp)
                        .width(60.dp)
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .shimmerEffect(
                            secondModifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            userScrollEnabled = false
        ){
            items(4) {
                Column (
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .width(235.dp)
                            .height(150.dp)
                            .background(Color.LightGray, RoundedCornerShape(10.dp))
                            .shimmerEffect(
                                secondModifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                            )
                    )
                    Spacer(modifier = Modifier
                        .height(20.dp)
                        .width(160.dp)
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .shimmerEffect(
                            secondModifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                    )
                    Spacer(modifier = Modifier
                        .height(15.dp)
                        .width(130.dp)
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .shimmerEffect(
                            secondModifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                    )
                    Spacer(modifier = Modifier
                        .height(15.dp)
                        .width(140.dp)
                        .background(Color.LightGray, RoundedCornerShape(10.dp))
                        .shimmerEffect(
                            secondModifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                        )
                    )

                }
            }
        }

        Spacer(
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                .shimmerEffect(
                    secondModifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                )
        )

    }
}

@Preview
@Composable
fun HomeShimmerPreview() {
    HomeShimmer()
}