package com.eco_trak.balance.ui.customers.update_customer

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun UpdateCustomerTopBar(
    navigateToCustomersScreen: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = "Update customer"
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