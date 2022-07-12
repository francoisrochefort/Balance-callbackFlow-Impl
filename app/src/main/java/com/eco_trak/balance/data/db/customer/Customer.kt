package com.eco_trak.balance.data.db.customer

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "customers", indices = [Index(value = ["name"], unique = true)])
data class Customer(
    @NonNull
    var name: String,
    val address: String?,
    val city: String?,
    val contact: String?,
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun toString() : String = name
}