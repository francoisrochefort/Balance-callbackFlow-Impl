package com.eco_trak.balance.ui.main

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun MainScreen(
    navigateToSettingsScreen: () -> Unit,
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        Log.d("ABRACADABRA", "LaunchedEffect")
        viewModel.load()
    }

    Scaffold(
        content = { padding ->
            MainContent(
                padding = padding,
                navigateToSettingsScreen = navigateToSettingsScreen
            )
        }
    )
}