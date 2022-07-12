package com.eco_trak.balance.repo.vehicle

import com.eco_trak.balance.data.db.vehicle.Vehicle
import com.eco_trak.balance.data.db.vehicle.VehicleDao

class VehicleRepositoryImpl(

    private val vehicleDao: VehicleDao

): VehicleRepository {
    override suspend fun getVehiclesFromRoom(customerId: Int) = vehicleDao.getVehicles(customerId)
    override suspend fun getSearchFromRoom(customerId: Int, text: String) = vehicleDao.getSearch(customerId, text)
    override suspend fun getVehicleFromRoom(id: Int) = vehicleDao.getVehicle(id)

    override suspend fun addVehicleToRoom(vehicle: Vehicle, replace: Boolean) = vehicleDao.addVehicle(vehicle)
    override suspend fun updateVehicleInRoom(vehicle: Vehicle) = vehicleDao.updateVehicle(vehicle)
    override suspend fun deleteVehicleFromRoom(vehicle: Vehicle) = vehicleDao.deleteVehicle(vehicle)
}