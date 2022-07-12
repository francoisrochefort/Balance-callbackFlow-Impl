package com.eco_trak.balance.ui.materials.add_material

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable

@Composable
fun AddMaterialTopBar(
    navigateToMaterialsScreen: () -> Unit
) {
    TopAppBar (
        title = {
            Text(
                text = "Add material"
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateToMaterialsScreen()
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