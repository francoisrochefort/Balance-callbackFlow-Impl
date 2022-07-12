package com.eco_trak.balance.repo.customer

import com.eco_trak.balance.data.db.customer.Customer
import com.eco_trak.balance.data.db.customer.CustomerDao

class CustomerRepositoryImpl(

    private val customerDao: CustomerDao

) : CustomerRepository {

    class CustomerAlreadyExists(val customer: Customer): Exception("Customer ${customer.name} already exists")

    override suspend fun getCustomersFromRoom() = customerDao.getCustomers()
    override suspend fun getSearchFromRoom(name: String) = customerDao.getSearch(name)
    override suspend fun getCustomerFromRoom(id: Int) = customerDao.getCustomer(id)

    override suspend fun addCustomerToRoom(
        customer: Customer,
        replace: Boolean
    ) : Long {
        val existing: Customer? = customerDao.getCustomerByName(customer.name)
        if (existing != null && !replace)
            throw CustomerAlreadyExists(customer)
        return customerDao.addCustomer(customer)
    }

    override suspend fun updateCustomerInRoom(
        customer: Customer,
        replace: Boolean
    ) {
        val existing: Customer? = customerDao.getCustomerByName(customer.name)
        if ((existing != null) && (existing.id != customer.id) && !replace)
            throw CustomerAlreadyExists(customer)

        // TODO: call updateCustomer instead?!!
        customerDao.addCustomer(customer)
    }

    override suspend fun deleteCustomerFromRoom(customer: Customer) = customerDao.deleteCustomer(customer)
}