package com.eco_trak.balance.ui.authentication

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AuthenticationScreen(
    id: Int,
    navigateToUsersScreens: () -> Unit,
    navigateToUpdateUserSettings: (id: Int) -> Unit,
    viewModel: AuthenticateViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getUser(id)
    }
    Scaffold(
        topBar = {
            AuthenticationTopBar(
                navigateToUsersScreen = navigateToUsersScreens
            )
        },
        content = { padding ->
            AuthenticationContent(
                id = id,
                padding = padding,
                navigateToUsersScreens = navigateToUsersScreens,
                navigateToUpdateUserSettings = { navigateToUpdateUserSettings(id) }
            )
        }
    )
}