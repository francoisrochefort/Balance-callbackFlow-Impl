package com.eco_trak.balance.ui.customers.customers

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
class CustomersViewModel @Inject constructor(
    private val repo: CustomerRepository
) : ViewModel() {

    // TODO: Use a single variable; both contain the a customer list. The only difference is that
    //  flows are emitted by a different producer. The thing is that are consumed by the same consumer
    var customers by mutableStateOf(emptyList<Customer>())
        private set
    var search by mutableStateOf(emptyList<Customer>())
        private set

    private var deleted: Customer? = null

    private val _event = Channel<ListEvent<Customer>>()
    val event = _event.receiveAsFlow()

    fun getCustomers() {
        viewModelScope.launch {
            repo.getCustomersFromRoom().collect { response ->
                customers = response
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

    fun deleteCustomer(customer: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                deleted = customer
                repo.deleteCustomerFromRoom(customer)
                _event.send(ListEvent.OnDelete(customer))
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }

    fun undoDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addCustomerToRoom(customer = deleted!!, false)
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }
}