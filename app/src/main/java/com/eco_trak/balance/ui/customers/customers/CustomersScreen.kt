package com.eco_trak.balance.ui.customers.customers

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.R
import com.eco_trak.balance.ui.components.list.*
import com.eco_trak.balance.ui.theme.MyCustomerMenuColor1
import com.eco_trak.balance.ui.theme.MyCustomerMenuColor2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomersScreen(
    navigateToAddCustomerScreen: () -> Unit,
    navigateToUpdateCustomerScreen: (id: Int) -> Unit,
    viewModel: CustomersViewModel = hiltViewModel()
) {
    val context: Context = LocalContext.current
    ListScreen(
        title = context.getString(R.string.customers),
        navigateToAddItemScreen = navigateToAddCustomerScreen,
        navigateToUpdateItemScreen = navigateToUpdateCustomerScreen,
        fetchList = {
            viewModel.getCustomers()
        },
        getList = {
            viewModel.customers
        },
        getSearch = {
            viewModel.search
        },
        search = { text ->
            viewModel.getSearch(text)
        },
        getItemId = {
            it.id
        },
        getItemText = {
            it.name
        },
        deleteItem = {
            viewModel.deleteCustomer(it)
        },
        colors = listOf(
            MyCustomerMenuColor1,
            MyCustomerMenuColor2
        ),
        getEventFlow = {
            viewModel.event
        },
        getSnackBarMessage = {
            context.getString(R.string.material_deleted)
        },
        undoDelete = {
            viewModel.undoDelete()
        }
    )
}