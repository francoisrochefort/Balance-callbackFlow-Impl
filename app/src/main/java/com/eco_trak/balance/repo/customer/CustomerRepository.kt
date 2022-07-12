package com.eco_trak.balance.repo.customer

import com.eco_trak.balance.data.db.customer.Customer
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {

    suspend fun getCustomersFromRoom(): Flow<List<Customer>>
    suspend fun getSearchFromRoom(name: String): Flow<List<Customer>>
    suspend fun getCustomerFromRoom(id: Int): Flow<Customer>

    suspend fun addCustomerToRoom(customer: Customer, replace: Boolean) : Long
    suspend fun updateCustomerInRoom(customer: Customer, replace: Boolean)
    suspend fun deleteCustomerFromRoom(customer: Customer)
}