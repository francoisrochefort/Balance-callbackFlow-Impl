package com.eco_trak.balance.ui.customers.add_customer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.customer.Customer
import com.eco_trak.balance.repo.customer.CustomerRepository
import com.eco_trak.balance.ui.components.list.ListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCustomerViewModel @Inject constructor(
    private val repo: CustomerRepository
) : ViewModel() {

    var customer by mutableStateOf(Customer("", "", "", ""))
        private set

    private val _event = Channel<ListEvent<Customer>>()
    val event = _event.receiveAsFlow()

    fun updateName(name: String) {
        customer = customer.copy(name = name)
    }

    fun updateAddress(address: String) {
        customer = customer.copy(address = address)
    }

    fun updateCity(city: String) {
        customer = customer.copy(city = city)
    }

    fun updateContact(contact: String) {
        customer = customer.copy(contact = contact)
    }

    fun addCustomer(replace: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val id: Long = repo.addCustomerToRoom(
                    customer = customer,
                    replace = replace
                )
                _event.send(
                    ListEvent.OnNew(
                        customer.copy(
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







