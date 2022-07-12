package com.eco_trak.balance.data.db.user

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "users",
    indices = [
        Index(
            value = ["name"],
            unique = true)
    ]
)
data class User(
    @NonNull
    val name: String,
    @NonNull
    val password: String,
    @NonNull
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
) {
    override fun toString(): String = name
}