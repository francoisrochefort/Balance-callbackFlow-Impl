package com.eco_trak.balance.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eco_trak.balance.ui.theme.MyButtonTextColor

@Composable
fun MyButton(
    text:String,
    onClick: () -> Unit,
    colors: List<Color>,
    modifier: Modifier = Modifier,
) {
    MyGradientBox(
        colors = colors,
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable { onClick() }
    ) {
        Text(
            text = text.uppercase(),
            Modifier
                .padding(
                    start = 20.dp,
                    top = 9.dp,
                    end = 20.dp,
                    bottom = 9.dp
                ),
            color = MyButtonTextColor
        )
    }
}

@Preview
@Composable
fun MyButtonPreview() {
    MyButton(
        text = "Button1",
        onClick = { },
        colors = listOf(
            Color(0xFF2F8DFD),
            Color(0xFF042058)
        )
    )
}