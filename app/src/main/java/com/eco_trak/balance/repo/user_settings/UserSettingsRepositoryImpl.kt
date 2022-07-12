package com.eco_trak.balance.repo.user_settings

import com.eco_trak.balance.data.db.user_settings.UserSettings
import com.eco_trak.balance.data.db.user_settings.UserSettingsDao

class UserSettingsRepositoryImpl(
    private val userSettingsDao: UserSettingsDao
): UserSettingsRepository {
    override suspend fun getUserSettingsFromRoom(user_id: Int) = userSettingsDao.getUserSettings(user_id)
    override suspend fun addUserSettingsFromRoom(userSettings: UserSettings) = userSettingsDao.addUserSettings(userSettings)

    override suspend fun updateUserSettingsFromRoom(userSettings: UserSettings) = userSettingsDao.updateUserSettings(userSettings)
    override suspend fun deleteUserSettingsFromRoom(userSettings: UserSettings) = userSettingsDao.deleteUserSettings(userSettings)
}