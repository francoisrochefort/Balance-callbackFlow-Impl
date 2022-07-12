package com.eco_trak.balance.ui.customer_vehicles.customer_vehicles

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.R
import com.eco_trak.balance.ui.components.list.ListScreen

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomerVehiclesScreen(
    navigateToAddCustomerVehicleScreen: (customerId: Int) -> Unit,
    navigateToUpdateCustomerVehicleScreen: (id: Int) -> Unit,
    customerId: Int,
    viewModel: CustomerVehiclesViewModel = hiltViewModel()
) {
    val context: Context = LocalContext.current
    ListScreen(
        title = "Customer Vehicles",
        navigateToAddItemScreen = {
            navigateToAddCustomerVehicleScreen(customerId)
        },
        navigateToUpdateItemScreen = { id ->
            navigateToUpdateCustomerVehicleScreen(id)
        },
        fetchList = {
            viewModel.getVehicles(customerId)
        },
        getList = {
            viewModel.vehicles
        },
        getSearch = {
            viewModel.search
        },
        search = { text ->
            viewModel.getSearch(customerId, text)
        },
        getItemId = {
            it.id
        },
        getItemText = {
            it.model
        },
        deleteItem = {
            viewModel.deleteVehicle(it)
        },
        colors = listOf(
            Color(0xFF2F8DFD),
            Color(0xFF042058)
        ),
        getEventFlow = {
            viewModel.event
        },
        getSnackBarMessage = {
            context.getString(R.string.vehicle_deleted)
        },
        undoDelete = {
            viewModel.undoDelete()
        }
    )
}