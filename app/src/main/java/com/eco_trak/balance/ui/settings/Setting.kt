package com.eco_trak.balance.ui.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eco_trak.balance.ui.components.MyCard
import com.eco_trak.balance.ui.components.MyGradientBox

@Composable
fun Setting(
    navigateTo: () -> Unit,
    name: String,
    modifier: Modifier = Modifier,
    colors: List<Color>
) {
    MyCard(
        onClick = {
            navigateTo()
        },
        modifier = modifier
    ) {
        MyGradientBox(
            colors = colors
        ) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(all = 12.dp)
            )
        }
    }
}