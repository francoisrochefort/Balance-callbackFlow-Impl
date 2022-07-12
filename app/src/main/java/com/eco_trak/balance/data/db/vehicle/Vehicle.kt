package com.eco_trak.balance.data.db.vehicle

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehicles"
)
data class Vehicle(
    var model: String,
    var number: String,
    var plate: String,
    var capacity: Float,

    @NonNull
    var customer_id: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun toString(): String {
        return model
    }
}