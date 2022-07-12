package com.eco_trak.balance.ui.materials.update_material

import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.repo.material.MaterialRepositoryImpl
import com.eco_trak.balance.ui.components.ObjectAlreadyExistExceptionAlertDialog
import com.eco_trak.balance.ui.components.list.ListEvent

@Composable
fun UpdateMaterialScreen(
    id: Int,
    navigateToMaterialsScreen: () -> Unit,
    viewModel: UpdateMaterialViewModel = hiltViewModel()
) {
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(key1 = true) {
        viewModel.getMaterial(id)
        viewModel.event.collect { event ->
            when (event) {
                is ListEvent.OnError -> {
                    when (event.exception) {
                        is MaterialRepositoryImpl.MaterialAlreadyExists -> {
                            showDialog = true
                        }
                    }
                }
                is ListEvent.OnUpdate -> {
                    navigateToMaterialsScreen()
                }
                else -> Unit
            }
        }
    }

    Scaffold(
        topBar = {
            UpdateMaterialTopBar(
                navigateToMaterialsScreen = navigateToMaterialsScreen
            )
        },
        content = { padding ->
            UpdateMaterialContent(
                padding = padding,
                id = id
            )
        }
    )

    if (showDialog) {
        ObjectAlreadyExistExceptionAlertDialog(
            hideDialog = {
                showDialog = false
            },
            onReplace = {
                viewModel.updateMaterial(true)
            }
        )
    }
}