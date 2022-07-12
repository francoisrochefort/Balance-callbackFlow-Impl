package com.eco_trak.balance.ui.settings

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SettingsScreen(
    map: Map<String, ()-> Unit>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            SettingsTopBar()
        },
        content = { padding ->
            SettingsContent(
                padding = padding,
                map = map
            )
        }
    )
}
