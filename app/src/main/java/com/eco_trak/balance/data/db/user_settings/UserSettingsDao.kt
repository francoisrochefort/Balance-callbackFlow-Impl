package com.eco_trak.balance.data.db.user_settings

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserSettingsDao {
    @Query("SELECT * FROM user_settings WHERE id = :user_id LIMIT 1")
    fun getUserSettings(user_id: Int): Flow<UserSettings>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun addUserSettings(userSettings: UserSettings)

    @Update
    fun updateUserSettings(userSettings: UserSettings)

    @Delete
    fun deleteUserSettings(userSettings: UserSettings)
}
