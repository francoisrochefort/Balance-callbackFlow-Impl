package com.eco_trak.balance.ui.users.add_user

import android.content.Context
import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.ui.components.list.ListEvent

@Composable
fun AddUserScreen(
    navigateToUsersScreen: () -> Unit,
    navigateToUserSettings: (id: Int) -> Unit,
    viewModel: AddUserViewModel = hiltViewModel()
) {
    val context: Context = LocalContext.current
    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when (event) {
                is ListEvent.OnNew -> {
                    navigateToUserSettings(event.item.id)
                }
                is ListEvent.OnError -> {
                    Toast.makeText(context, event.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        topBar = {
            AddUserTopBar(
                navigateToUsersScreen = navigateToUsersScreen
            )
        },
        content = { padding ->
            AddUserContent(
                padding = padding
            )
        }
    )
}