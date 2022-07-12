package com.eco_trak.balance.ui.customer_vehicles.update_vehicle

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun UpdateVehicleScreen(
    id: Int,
    navigateToVehiclesScreen: () -> Unit,
    viewModel: UpdateVehicleViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getVehicle(id)
    }
    Scaffold(
        topBar = {
            UpdateVehicleTopBar(
                navigateToVehiclesScreen = navigateToVehiclesScreen
            )
        },
        content = { padding ->
            UpdateVehicleContent(
                padding = padding,
                id = id,
                navigateToVehiclesScreen = navigateToVehiclesScreen
            )
        }
    )
}