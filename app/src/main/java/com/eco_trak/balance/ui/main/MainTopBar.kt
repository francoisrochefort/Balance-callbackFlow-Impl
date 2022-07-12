package com.eco_trak.balance.ui.main

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable

@Composable
fun MainTopBar(
) {
    TopAppBar (
        title = {
            Text(
                text = "Balance"
            )
        }
    )
}