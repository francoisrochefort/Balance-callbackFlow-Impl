package com.eco_trak.balance.ui.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
@ExperimentalMaterialApi
fun <T>ListContent(
    padding: PaddingValues,
    navigateToUpdateItemScreen: (id: Int) -> Unit,
    getList: () -> List<T>,
    getSearch: () -> List<T>,
    search: (text: String) -> Unit,
    getItemId: (item: T) -> Int,
    getItemText: (item: T) -> String,
    deleteItem: (item: T) -> Unit,
    colors: List<Color>
) {
    var text by remember { mutableStateOf("") }

    Column {
        ListSearchBar(
            padding = padding,
            text = text,
            onTextChange = {
                search(it)
                text = it
            }
        )
        val list: List<T> = if (text.isNotEmpty()) getSearch() else getList()
        ItemList(
            padding = padding,
            navigateToUpdateCustomerScreen = navigateToUpdateItemScreen,
            modifier = Modifier
                .weight(1f),
            list = list,
            getItemId = getItemId,
            getItemText = getItemText,
            deleteItem = deleteItem,
            colors = colors
        )
        ListFooter(
            size = list.size,
            modifier = Modifier.padding(10.dp)
        )
    }
}