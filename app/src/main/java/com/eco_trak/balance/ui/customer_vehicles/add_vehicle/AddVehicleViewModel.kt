package com.eco_trak.balance.ui.customer_vehicles.add_vehicle

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.vehicle.Vehicle
import com.eco_trak.balance.repo.vehicle.VehicleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddVehicleViewModel @Inject constructor(
    private val repo: VehicleRepository
) : ViewModel() {

    var vehicle by mutableStateOf(Vehicle("", "", "", 0f, 0))
        private set

    fun updateModel(model: String) {
        vehicle = vehicle.copy(model = model)
    }

    fun updateNumber(number: String) {
        vehicle = vehicle.copy(number = number)
    }

    fun updatePlate(plate: String) {
        vehicle = vehicle.copy(plate = plate)
    }

    fun updateCapacity(capacity: Float) {
        vehicle = vehicle.copy(capacity = capacity)
    }

    fun addVehicle(customerId: Int, replace: Boolean = false) {
        viewModelScope.launch(Dispatchers.IO) {
            vehicle.customer_id = customerId
            repo.addVehicleToRoom(
                vehicle = vehicle,
                replace = replace
            )
        }
    }
}