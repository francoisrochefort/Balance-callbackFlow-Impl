package com.eco_trak.balance.ui.customer_vehicles.customer_vehicles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.vehicle.Vehicle
import com.eco_trak.balance.repo.vehicle.VehicleRepository
import com.eco_trak.balance.ui.components.list.ListEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CustomerVehiclesViewModel  @Inject constructor(
    private val repo: VehicleRepository
): ViewModel() {

    var vehicles by mutableStateOf(emptyList<Vehicle>())
        private set
    var search by mutableStateOf(emptyList<Vehicle>())
        private set

    private var deleted: Vehicle? = null
    private val _event = Channel<ListEvent<Vehicle>>()
    val event = _event.receiveAsFlow()

    fun getVehicles(customerId: Int) {
        viewModelScope.launch {
            repo.getVehiclesFromRoom(customerId).collect { response ->
                vehicles = response
            }
        }
    }

    fun getSearch(customerId: Int, text: String) {
        viewModelScope.launch {
            repo.getSearchFromRoom(customerId, text).collect { response ->
                search = response
            }
        }
    }

    fun deleteVehicle(vehicle: Vehicle) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteVehicleFromRoom(vehicle)
        }
    }

    fun undoDelete() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repo.addVehicleToRoom(vehicle = deleted!!, false)
            }
            catch (e: Exception) {
                _event.send(ListEvent.OnError(e))
            }
        }
    }
}