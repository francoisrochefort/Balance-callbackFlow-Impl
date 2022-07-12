package com.eco_trak.balance.ui.authentication

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AuthenticationTopBar(
    navigateToUsersScreen: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = "Authentication"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateToUsersScreen()
                }
            ) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back.",
                )
            }
        }
    )
}