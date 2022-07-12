package com.eco_trak.balance.ui.customer_vehicles.update_vehicle

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
class UpdateVehicleViewModel @Inject constructor(
    private val repo: VehicleRepository
) : ViewModel() {

    var vehicle by mutableStateOf(Vehicle("", "", "", 0f, 0))
        private set

    fun getVehicle(id: Int) {
        viewModelScope.launch {
            repo.getVehicleFromRoom(id).collect { response ->
                vehicle = response
            }
        }
    }

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

    fun updateVehicle() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateVehicleInRoom(vehicle)
        }
    }
}