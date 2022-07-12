package com.eco_trak.balance.ui.customers.update_customer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.customer.Customer
import com.eco_trak.balance.repo.customer.CustomerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class UpdateCustomerEvent {
    data class OnUpdate(val customer: Customer): UpdateCustomerEvent()
    data class OnError(val exception: Exception) : UpdateCustomerEvent()
}

@HiltViewModel
class UpdateCustomerViewModel @Inject constructor(
    private val repo: CustomerRepository
) : ViewModel() {

    var customer by mutableStateOf(Customer("", "", "", ""))

    private val _event = Channel<UpdateCustomerEvent>()
    val event = _event.receiveAsFlow()

    fun getCustomer(id: Int) {
        viewModelScope.launch {
            repo.getCustomerFromRoom(id).collect { response ->
                customer = response
            }
        }
    }

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

    fun updateCustomer(replace: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.updateCustomerInRoom(
                    customer = customer,
                    replace = replace
                )
                _event.send(UpdateCustomerEvent.OnUpdate(customer))
            }
            catch (e: Exception) {
                _event.send(UpdateCustomerEvent.OnError(e))
            }
        }
    }
}