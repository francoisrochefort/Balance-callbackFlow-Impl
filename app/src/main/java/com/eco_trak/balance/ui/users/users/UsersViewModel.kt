package com.eco_trak.balance.ui.users.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.user.User
import com.eco_trak.balance.repo.user.UserRepository
import com.eco_trak.balance.ui.components.list.ListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    var users by mutableStateOf(emptyList<User>())
        private set

    var search by mutableStateOf(emptyList<User>())
        private set

    private var deleted: User? = null
    private val _event = Channel<ListEvent<User>>()
    val event = _event.receiveAsFlow()

    fun getUsers() {
        viewModelScope.launch {
            repo.getUsersFromRoom().collect { response ->
                users = response
            }
        }
    }

    fun getSearch(text: String) {
        viewModelScope.launch {
            repo.getSearchFromRoom(text).collect { response ->
                search = response
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleted = user
                repo.deleteUserFromRoom(user)
                _event.send(ListEvent.OnDelete(user))
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }

    fun undoDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addUserToRoom(user = deleted!!, false)
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }
}