package com.eco_trak.balance.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.eco_trak.balance.data.db.customer.Customer
import com.eco_trak.balance.data.db.customer.CustomerDao
import com.eco_trak.balance.data.db.material.Material
import com.eco_trak.balance.data.db.material.MaterialDao
import com.eco_trak.balance.data.db.user.User
import com.eco_trak.balance.data.db.user.UserDao
import com.eco_trak.balance.data.db.user_settings.UserSettings
import com.eco_trak.balance.data.db.user_settings.UserSettingsDao
import com.eco_trak.balance.data.db.vehicle.Vehicle
import com.eco_trak.balance.data.db.vehicle.VehicleDao

@Database(
    entities = [
        Customer::class,
        Vehicle::class,
        Material::class,
        User::class,
        UserSettings::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class Db: RoomDatabase() {

    /*companion object {
        lateinit var converters: Converters
    }*/

    abstract fun customerDao(): CustomerDao
    abstract fun vehicleDao(): VehicleDao
    abstract fun materialDao(): MaterialDao
    abstract fun userDao(): UserDao
    abstract fun userSettingsDao(): UserSettingsDao
}