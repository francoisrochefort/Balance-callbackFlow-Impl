package com.eco_trak.balance.ui.customers.add_customer

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AddCustomerTopBar(
    navigateToCustomersScreen: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = "Add customer"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateToCustomersScreen()
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