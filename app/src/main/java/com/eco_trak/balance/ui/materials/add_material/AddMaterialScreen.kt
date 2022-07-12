package com.eco_trak.balance.ui.materials.add_material

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.repo.material.MaterialRepositoryImpl
import com.eco_trak.balance.ui.components.ObjectAlreadyExistExceptionAlertDialog
import com.eco_trak.balance.ui.components.list.ListEvent

@Composable
fun AddMaterialScreen(
    navigateToMaterialsScreen: () -> Unit,
    viewModel: AddMaterialViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        viewModel.event.collect { event ->
            when (event) {
                is ListEvent.OnError -> {
                    when (event.exception) {
                        is MaterialRepositoryImpl.MaterialAlreadyExists -> {
                            showDialog = true
                        }
                    }
                }
                is ListEvent.OnNew -> {
                    navigateToMaterialsScreen()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            AddMaterialTopBar(
                navigateToMaterialsScreen = navigateToMaterialsScreen
            )
        },
        content = { padding ->
            AddMaterialContent(
                padding = padding
            )
        }
    )

    if (showDialog) {
        ObjectAlreadyExistExceptionAlertDialog(
            hideDialog = {
                showDialog = false
            },
            onReplace = {
                viewModel.addMaterial(true)
            }
        )
    }
}