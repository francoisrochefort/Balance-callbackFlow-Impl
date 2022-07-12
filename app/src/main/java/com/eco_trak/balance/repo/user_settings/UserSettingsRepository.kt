package com.eco_trak.balance.repo.user_settings

import com.eco_trak.balance.data.db.user_settings.UserSettings
import kotlinx.coroutines.flow.Flow

interface UserSettingsRepository {
    suspend fun getUserSettingsFromRoom(user_id: Int): Flow<UserSettings>
    suspend fun addUserSettingsFromRoom(userSettings: UserSettings)

    suspend fun updateUserSettingsFromRoom(userSettings: UserSettings)
    suspend fun deleteUserSettingsFromRoom(userSettings: UserSettings)
}