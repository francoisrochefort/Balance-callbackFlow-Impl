package com.eco_trak.balance.repo.vehicle

import com.eco_trak.balance.data.db.vehicle.Vehicle
import kotlinx.coroutines.flow.Flow

interface VehicleRepository {
    suspend fun getVehiclesFromRoom(customerId: Int): Flow<List<Vehicle>>
    suspend fun getSearchFromRoom(customerId: Int, text: String): Flow<List<Vehicle>>
    suspend fun getVehicleFromRoom(id: Int): Flow<Vehicle>

    suspend fun addVehicleToRoom(vehicle: Vehicle, replace: Boolean): Long
    suspend fun updateVehicleInRoom(vehicle: Vehicle)
    suspend fun deleteVehicleFromRoom(vehicle: Vehicle)
}