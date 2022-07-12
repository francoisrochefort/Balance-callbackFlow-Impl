package com.eco_trak.balance.ui.components.list

import android.content.Context
import android.widget.Toast
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.eco_trak.balance.R
import kotlinx.coroutines.flow.Flow

@Composable
@ExperimentalMaterialApi
fun <T>ListScreen(
    title: String,
    navigateToAddItemScreen: () -> Unit,
    navigateToUpdateItemScreen: (id: Int) -> Unit,
    fetchList: () -> Unit,
    getList: () -> List<T>,
    getSearch: () -> List<T>,
    search: (text: String) -> Unit,
    getItemId: (item: T) -> Int,
    getItemText: (item: T) -> String,
    deleteItem: (item: T) -> Unit,
    colors: List<Color>,
    getEventFlow: () -> Flow<ListEvent<T>>,
    getSnackBarMessage: () -> String,
    undoDelete: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val context: Context = LocalContext.current
    LaunchedEffect(key1 = true) {
        fetchList()
        getEventFlow().collect { event ->
            when (event) {
                is ListEvent.OnDelete -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = getSnackBarMessage(),
                        actionLabel = context.getString(R.string.undo)
                    )
                    if (result == SnackbarResult.ActionPerformed)
                        undoDelete()
                }
                is ListEvent.OnError -> {
                    Toast.makeText(context, event.exception.message, Toast.LENGTH_SHORT).show()
                }
                else -> Unit
            }
        }
    }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            ListTopBar(
                title = title
            )
        },
        floatingActionButton = {
            AddListItemFAB(
                navigateToAddItemScreen = navigateToAddItemScreen
            )
        },
        content = { padding ->
            ListContent(
                padding = padding,
                navigateToUpdateItemScreen = navigateToUpdateItemScreen,
                getList = getList,
                getSearch = getSearch,
                search = search,
                getItemId = getItemId,
                getItemText = getItemText,
                deleteItem = deleteItem,
                colors = colors
            )
        }
    )
}