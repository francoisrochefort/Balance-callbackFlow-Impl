package com.eco_trak.balance.ui.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.customer.Customer
import com.eco_trak.balance.data.db.material.Material
import com.eco_trak.balance.data.db.user.User
import com.eco_trak.balance.data.db.vehicle.Vehicle
import com.eco_trak.balance.repo.customer.CustomerRepository
import com.eco_trak.balance.repo.material.MaterialRepository
import com.eco_trak.balance.repo.user.UserRepository
import com.eco_trak.balance.repo.vehicle.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(

    private val userRepository: UserRepository,
    private val customerRepository: CustomerRepository,
    private val vehicleRepository: VehicleRepository,
    private val materialRepository: MaterialRepository

) : ViewModel() {

    var tare: Float by mutableStateOf(1000f)
    var degree: Float by mutableStateOf(0f)
    var currentWeight: Float by mutableStateOf(1_000_000f)
    var lastBucket: Float by mutableStateOf(100_000f)
    var bucketCount: Int by mutableStateOf(100)
    var expectedLoad: Float by mutableStateOf(1_000_000f)
    var totalLoad: Float by mutableStateOf(1_000_000f)

    var users by mutableStateOf(emptyList<User>())
        private set

    var customers by mutableStateOf(emptyList<Customer>())
        private set

    var vehicles by mutableStateOf(emptyList<Vehicle>())
        private set

    var materials by mutableStateOf(emptyList<Material>())
        private set

    var selectedUser: User? by mutableStateOf(null)
        private set

    var selectedCustomer: Customer? by mutableStateOf(null)
        private set

    var selectedVehicle: Vehicle? by mutableStateOf(null)
        private set

    var selectedMaterial: Material? by mutableStateOf(null)
        private set

    private fun getUsers() {
        viewModelScope.launch {
            userRepository.getUsersFromRoom().collect { response ->
                users = response
            }
        }
    }

    private fun getCustomers() {
        viewModelScope.launch {
            customerRepository.getCustomersFromRoom().collect {
                customers = it
            }
        }
    }

    private fun getVehicles() {
        selectedCustomer?.let { customer ->
            viewModelScope.launch {
                vehicleRepository.getVehiclesFromRoom(customer.id).collect { response ->
                    vehicles = response
                }
            }
        }
    }

    private fun getMaterials() {
        viewModelScope.launch {
            materialRepository.getMaterialsFromRoom().collect { response ->
                materials = response
            }
        }
    }

    suspend fun load() {

        getUsers()
        getCustomers()
        getVehicles()
        getMaterials()

        viewModelScope.launch {
            selectedCustomer?.let {
                customerRepository.getCustomerFromRoom(it.id).collect { customer ->
                    selectedCustomer = customer
                }
            }
        }
    }

    fun updateUser(user: User) {
        selectedUser = user
    }

    fun updateCustomer(customer: Customer) {

        // Check if we need to update the UI
        if (customer != selectedCustomer) {

            // If so then clear the selected vehicle
            selectedVehicle = null

            // Update the vehicle list
            viewModelScope.launch {
                vehicleRepository.getVehiclesFromRoom(customer!!.id).collect { list ->
                    vehicles = list
                }
            }

            // Update the selected customer
            selectedCustomer = customer
        }
    }

    fun updateVehicle(vehicle: Vehicle) {
        selectedVehicle = vehicle
    }

    fun updateMaterial(material: Material) {
        selectedMaterial = material
    }
}
