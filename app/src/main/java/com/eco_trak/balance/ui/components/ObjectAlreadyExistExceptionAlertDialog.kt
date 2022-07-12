package com.eco_trak.balance.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun  ObjectAlreadyExistExceptionAlertDialog(
    hideDialog: () -> Unit,
    onReplace: () -> Unit
) {
    AlertDialog(
        title = {
            Text(text = "An error has occurred")
        },
        text = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text =
                    "The data your are trying to submit already exist.\n" +
                    "What do you want to do?"
                )
            }
        },
        onDismissRequest = {
            hideDialog()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onReplace()
                    hideDialog()
                }
            ) {
                Text(text = "Replace")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    hideDialog()
                }
            ) {
                Text(text = "Cancel")
            }
        }
    )
}
