package com.eco_trak.balance.ui.users.users

import android.content.Context
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.R
import com.eco_trak.balance.ui.components.list.ListScreen
import com.eco_trak.balance.ui.theme.MyUserMenuColor1
import com.eco_trak.balance.ui.theme.MyUserMenuColor2

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UsersScreen(
    navigateToAddUserScreen: () -> Unit,
    navigateToAuthenticationScreen: (id: Int) -> Unit,
    viewModel: UsersViewModel = hiltViewModel()
) {
    val context: Context = LocalContext.current
    ListScreen(
        title = "Users",
        navigateToAddItemScreen = navigateToAddUserScreen,
        navigateToUpdateItemScreen = navigateToAuthenticationScreen,
        fetchList = {
            viewModel.getUsers()
        },
        getList = {
            viewModel.users
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
            viewModel.deleteUser(it)
        },
        colors = listOf(
            MyUserMenuColor1,
            MyUserMenuColor2
        ),
        getEventFlow = {
            viewModel.event
        },
        getSnackBarMessage = {
            context.getString(R.string.user_deleted)
        },
        undoDelete = {
            viewModel.undoDelete()
        }
    )
}