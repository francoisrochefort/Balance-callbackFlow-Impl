package com.eco_trak.balance.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MyGradientBox(
    colors: List<Color>,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable () -> Unit
) {
    Box(
        contentAlignment = contentAlignment,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(Brush.verticalGradient(colors = colors)
        )
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun GradientBoxPreview() {
    MyGradientBox(
        listOf(
            Color(0xFF2F8DFD),
            Color(0xFF042058)
        )
    ) {
        Text(text = "GradientBox")
    }
}