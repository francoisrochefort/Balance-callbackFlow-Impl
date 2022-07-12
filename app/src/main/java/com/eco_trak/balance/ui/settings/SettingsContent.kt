package com.eco_trak.balance.ui.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun SettingsContent(
    padding: PaddingValues,
    map: Map<String, ()-> Unit>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(padding)
    ) {
        for ((k, v) in map) {
            Setting(
                navigateTo = v,
                name = k.uppercase(),
                colors = listOf(
                    Color(0xFF2F8DFD),
                    Color(0xFF042058)
                )
            )
        }
    }
}

