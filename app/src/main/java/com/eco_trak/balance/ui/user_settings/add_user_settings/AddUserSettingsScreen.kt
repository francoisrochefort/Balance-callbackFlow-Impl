package com.eco_trak.balance.ui.user_settings.add_user_settings

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AddUserSettingsScreen(
    navigateToUsersScreen: () -> Unit,
    userId: Int
) {
    Scaffold(
        topBar = {
            AddUserSettingsTopBar(
                navigateToUsersScreen = navigateToUsersScreen
            )
        },
        content = { padding ->
            AddUserSettingsContent(
                padding = padding,
                navigateToUsersScreen = navigateToUsersScreen,
                userId
            )
        }
    )
}