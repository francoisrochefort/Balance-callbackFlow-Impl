package com.eco_trak.balance.ui.user_settings.update_user_settings

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UpdateUserSettingsScreen(
    id: Int,
    navigateToUsersScreen: () -> Unit,
    viewModel: UpdateUserSettingsViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getUserSettings(id)
    }
    Scaffold(
        topBar = {
            UpdateUserSettingsTopBar(
                navigateToUsersScreen = navigateToUsersScreen
            )
        },
        content = { padding ->
            UpdateUserSettingsContent(
                padding = padding,
                id = id,
                navigateToUsersScreen = navigateToUsersScreen
            )
        }
    )
}
