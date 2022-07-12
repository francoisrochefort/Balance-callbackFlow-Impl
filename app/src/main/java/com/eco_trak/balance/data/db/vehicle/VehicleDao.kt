package com.eco_trak.balance.data.db.vehicle

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Query("SELECT * FROM vehicles WHERE customer_id = :customerId ORDER BY model ASC")
    fun getVehicles(customerId: Int): Flow<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE customer_id = :customerId AND model LIKE :text || '%' ORDER BY model ASC")
    fun getSearch(customerId: Int, text: String): Flow<List<Vehicle>>

    @Query("SELECT * FROM vehicles WHERE id = :id")
    fun getVehicle(id: Int): Flow<Vehicle>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addVehicle(vehicle: Vehicle) : Long

    @Update
    fun updateVehicle(vehicle: Vehicle)

    @Delete
    fun deleteVehicle(vehicle: Vehicle)
}
