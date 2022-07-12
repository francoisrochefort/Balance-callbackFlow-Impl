package com.eco_trak.balance.ui.materials.materials

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.R
import com.eco_trak.balance.ui.components.list.ListScreen
import com.eco_trak.balance.ui.theme.MyMaterialMenuColor1
import com.eco_trak.balance.ui.theme.MyMaterialMenuColor2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MaterialsScreen(
    navigateToAddMaterialScreen: () -> Unit,
    navigateToUpdateMaterialScreen: (id: Int) -> Unit,
    viewModel: MaterialsViewModel = hiltViewModel()
) {
    val context: Context = LocalContext.current
    ListScreen(
        title = context.getString(R.string.materials),
        navigateToAddItemScreen = navigateToAddMaterialScreen,
        navigateToUpdateItemScreen = navigateToUpdateMaterialScreen,
        fetchList = {
            viewModel.getMaterials()
        },
        getList = {
            viewModel.materials
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
            viewModel.deleteMaterial(it)
        },
        colors = listOf(
            MyMaterialMenuColor1,
            MyMaterialMenuColor2
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