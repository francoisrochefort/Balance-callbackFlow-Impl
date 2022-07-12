package com.eco_trak.balance.ui.components.list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T>ItemList(
    padding: PaddingValues,
    navigateToUpdateCustomerScreen: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
    list: List<T>,
    getItemId: (item: T) -> Int,
    getItemText: (item: T) -> String,
    deleteItem: (item: T) -> Unit,
    colors: List<Color>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(padding)) {
        items(
            items = list
        ) { item ->
            ListItem(
                navigateToUpdateItemScreen = navigateToUpdateCustomerScreen,
                item = item,
                getItemId = getItemId,
                getItemText = getItemText,
                deleteItem = deleteItem,
                colors = colors
            )
        }
    }
}