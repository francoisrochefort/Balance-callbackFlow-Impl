package com.eco_trak.balance.ui.components.list

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eco_trak.balance.R
import com.eco_trak.balance.data.db.material.Material
import com.eco_trak.balance.ui.components.MyCard
import com.eco_trak.balance.ui.components.MyGradientBox
import com.eco_trak.balance.ui.theme.*

@Composable
@ExperimentalMaterialApi
fun <T>ListItem(
    item: T,
    navigateToUpdateItemScreen: (id: Int) -> Unit,
    getItemId: (item: T) -> Int,
    getItemText: (item: T) -> String,
    deleteItem: (item: T) -> Unit,
    colors: List<Color>
) {
    val context: Context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    MyCard(
        onClick = {
            navigateToUpdateItemScreen(getItemId(item))
        }
    ) {
        MyGradientBox(
            colors = colors
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(0.90f)
                ) {
                    Text(
                        text = getItemText(item),
                        color = MyListItemTextColor
                    )
                }
                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete item",
                        tint = MyListItemIconColor
                    )
                }
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = context.getString(R.string.delete_confirmation_dialog_title))
            },
            text = {
                Text(
                    text = String.format(
                        context.getString(
                            R.string.delete_confirmation_dialog_message
                        ),
                        getItemText(item)
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        deleteItem(item)
                        showDialog = false
                    }
                ) {
                    Text(context.getString(R.string.delete_confirmation_dialog_confirm_button))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text(context.getString(R.string.delete_confirmation_dialog_cancel_button))
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun ListItemPreview() {
    ListItem(
        item = Material(name = "Gold"),
        navigateToUpdateItemScreen = {},
        getItemId = { material ->
            material.id
        },
        getItemText = { material ->
            material.name
        },
        deleteItem = { },
        colors = listOf(MyButtonColor1, MyButtonColor2)
    )
}