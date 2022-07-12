package com.eco_trak.balance.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.toSize
import com.eco_trak.balance.R

@Composable
fun <T> MyDropDownMenu(
    hint: String,
    text: String?,
    list: List<T>,
    getItemText: (item: T) -> String,
    onClick: (item: T) -> Unit,
    colors: List<Color>,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    var width by remember { mutableStateOf(Size.Zero) }

    Column(modifier = modifier) {
        MyButton(
            onClick = {
                expanded = true
            },
            text = if (text == null) "$hint:${stringResource(R.string.none)}" else "$hint: $text",
            colors = colors,
            modifier = modifier
                .onGloballyPositioned {
                    width = it.size.toSize()
                }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { width.width.toDp() })
        ) {
            for (item: T in list) {
                DropdownMenuItem(
                    onClick = {
                        expanded = false
                        onClick(item)
                    }
                ) {
                    Text(text = getItemText(item))
                }
            }
        }
    }
}
