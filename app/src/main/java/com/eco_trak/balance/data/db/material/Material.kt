package com.eco_trak.balance.data.db.material

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "materials", indices = [Index(value = ["name"], unique = true)])
data class Material(
    @NonNull
    var name: String,
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun toString(): String = name
}