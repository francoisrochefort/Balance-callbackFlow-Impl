package com.eco_trak.balance.ui.components.list

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable

@Composable
fun AddListItemFAB(
    navigateToAddItemScreen: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            navigateToAddItemScreen()
        },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add a customer"
        )
    }
}