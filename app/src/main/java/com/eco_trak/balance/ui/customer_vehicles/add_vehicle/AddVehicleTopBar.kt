package com.eco_trak.balance.ui.customer_vehicles.add_vehicle

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AddVehicleTopBar(
    navigateToVehiclesScreen: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = "Add vehicle"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateToVehiclesScreen()
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