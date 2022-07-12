package com.eco_trak.balance.ui.users.add_user

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
class AddUserViewModel @Inject constructor(
    private val repo: UserRepository
) : ViewModel() {

    var user by mutableStateOf(User("", ""))
        private set

    private val _event = Channel<ListEvent<User>>()
    val event = _event.receiveAsFlow()

    fun updateName(name: String) {
        user = user.copy(name = name)
    }

    fun updatePassword(password: String) {
        user = user.copy(password = password)
    }

    fun addUser(replace: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val id: Long = repo.addUserToRoom(
                    user = user,
                    replace = replace
                )
                _event.send(
                    ListEvent.OnNew(
                        user.copy(
                            id = id.toInt()
                        )
                    )
                )
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }
}