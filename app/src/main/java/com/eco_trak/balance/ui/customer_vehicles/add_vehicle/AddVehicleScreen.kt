package com.eco_trak.balance.ui.customer_vehicles.add_vehicle

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable

@Composable
fun AddVehicleScreen(
    navigateToVehiclesScreen: () -> Unit,
    customerId: Int
) {
    Scaffold(
        topBar = {
            AddVehicleTopBar(
                navigateToVehiclesScreen = navigateToVehiclesScreen
            )
        },
        content = { padding ->
            AddVehicleContent(
                padding = padding,
                navigateToVehiclesScreen = navigateToVehiclesScreen,
                customerId
            )
        }
    )
}