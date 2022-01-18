package me.spica.wuqi.widget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun DateView(
    month: String,
    day: String,
    modifier: Modifier
) {

    BoxWithConstraints(
        modifier = modifier
    ) {

        Canvas(
            modifier = Modifier
                .height(75.dp)
                .width(75.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawLine(
                color = Color.White,
                strokeWidth = 4.dp.value,
                start = Offset(x = 0f, y = canvasHeight),
                end = Offset(x = canvasWidth, y = 0f)
            )
        }

        Text(
            text = month,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.TopStart),
            color = Color.White
        )

        Text(
            text = day,
            fontSize = 18.sp,
            modifier = Modifier.align(Alignment.BottomEnd),
            color = Color.White
        )

    }

}