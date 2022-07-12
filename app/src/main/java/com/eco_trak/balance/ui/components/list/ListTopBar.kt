package com.eco_trak.balance.ui.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun ListTopBar(
    title: String
) {
    TopAppBar (
        title = {
            Column {
                Text(
                    text = title
                )
            }
        }
    )
}