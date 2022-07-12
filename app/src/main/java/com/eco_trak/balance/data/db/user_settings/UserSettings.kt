package com.eco_trak.balance.data.db.user_settings

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.eco_trak.balance.data.db.user.User
import java.util.*

@Entity(
    tableName = "user_settings",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class UserSettings(
    @ColumnInfo(name = "enable_vehicle_management")
    var enableVehicleManagement: Boolean,
    @ColumnInfo(name = "date_time")
    var dateTime: Date?,
    var language: String,
    @ColumnInfo(name = "company_info")
    var companyInfo: String,
    @ColumnInfo(name = "coupon_number")
    var couponNumber: String,
    @NonNull
    @PrimaryKey
    var id: Int = 0
)