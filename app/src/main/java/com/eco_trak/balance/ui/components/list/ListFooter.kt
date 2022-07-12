package com.eco_trak.balance.ui.components.list

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ListFooter(
    size: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$size items",
        modifier = modifier
            .wrapContentHeight()
    )
}