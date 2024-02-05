package com.malisoftware.components.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderColors
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.malisoftware.theme.AppTheme
import kotlin.math.abs
import kotlin.math.sin


@Composable
fun CoreRangeSlider(
    modifier: Modifier = Modifier,
    startValue: Float = 0f,
    endValue: Float = 100f,
    steps: Int = 100,
    onValueChangeFinished: (ClosedFloatingPointRange<Float>) -> Unit = {},
    topContent: @Composable (ClosedFloatingPointRange<Float>) -> Unit = {},
    content: @Composable (Float,Float) -> Unit = {_,_ ->},
    colors: SliderColors = SliderDefaults.colors()
) {
    var sliderPosition by remember { mutableStateOf(startValue..endValue) }
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        topContent(sliderPosition)
        RangeSlider(
            value = sliderPosition,
            steps = steps,
            onValueChange = { range -> sliderPosition = range },
            valueRange = startValue..endValue,
            onValueChangeFinished = { onValueChangeFinished.invoke(sliderPosition) },
            modifier = Modifier,
            colors = colors
        )
        content(sliderPosition.start, sliderPosition.endInclusive)
    }
}

/**
 * A composable that displays a range slider along with a custom bar chart.
 *
 * @param startValue The starting value of the range slider.
 * @param endValue The ending value of the range slider.
 * @param steps The number of steps for the range slider.
 * @param onValueChangeFinished Callback triggered when the range slider value is changed and the user stops interaction.
 */
@Composable
fun RangeSliderWithGraph(
    modifier: Modifier = Modifier,
    graphModifier: Modifier = Modifier,
    startValue: Float = 0f,
    endValue: Float = 100f,
    steps: Int = 100,
    onValueChangeFinished: (ClosedFloatingPointRange<Float>) -> Unit = {},
    size: Int = 32,
    content: @Composable (Float,Float) -> Unit = {_,_ ->},
    colors: SliderColors = SliderDefaults.colors()
) {
    @Composable
    fun Graph(
        sliderPosition: ClosedFloatingPointRange<Float>,
    ) = Graph(
        modifier = graphModifier,
        endValue = endValue,
        sliderPosition = sliderPosition,
        barSize = size
    )
    CoreRangeSlider(
        modifier = modifier,
        startValue = startValue,
        endValue = endValue,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        topContent = { Graph(it) },
        content = content,
        colors = colors
    )
}

@Composable
fun Graph(
    modifier: Modifier = Modifier,
    endValue: Float,
    sliderPosition: ClosedFloatingPointRange<Float>,
    barSize: Int = 32,
) {
    Row(
        modifier = modifier
            .rotate(180f)
            .offset(y = (-25).dp),
    ) {
        for (i in barSize downTo 1) {
            // Calculate the gap between bars
            // let say the endValue is 100, then the gap is 100/32 = 3.125
            // you need to multiply the gap by the index to get the position of the slider
            val gap = endValue / barSize
            val height = abs( 70 * sin(i.toFloat())).dp
            Canvas(
                modifier = Modifier
                    .width(8.dp)
                    .padding(start = 1.dp, end = 1.dp)
                    .height(height)
            ) {
                drawRect(
                    color = if (i * gap in sliderPosition) Color.Black else Color.LightGray,
                    style = Fill,
                    size = Size(size.width, size.height)
                )
            }
        }
    }
}

@Composable
fun RangeSliderWithData(
    modifier: Modifier = Modifier,
    dataModifier: Modifier = Modifier,
    startValue: Float = 0f,
    endValue: Float = 100f,
    onValueChangeFinished: (ClosedFloatingPointRange<Float>) -> Unit = {},
    data: List<String> = listOf(),
    content: @Composable (Float,Float) -> Unit = {_,_ ->},
    colors: SliderColors = SliderDefaults.colors()
){
    @Composable
    fun Data() {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = dataModifier
                .offset(y = (10).dp),
        ) {
            data.forEach {
                Text(text = it, style = AppTheme.typography.titleMedium)
            }
        }
    }
    CoreRangeSlider(
        modifier = modifier,
        startValue = startValue,
        endValue = endValue,
        steps = if (data.isNotEmpty() && data.size > 2) data.size-2 else data.size,
        onValueChangeFinished = onValueChangeFinished,
        topContent = { Data() },
        content = content,
        colors = colors
    )
}

@Preview
@Composable
fun RangeSliderExample_ () {
    RangeSliderWithData(
        data = listOf("1","2","3","4",),
        dataModifier = Modifier.fillMaxWidth(),
    )
}