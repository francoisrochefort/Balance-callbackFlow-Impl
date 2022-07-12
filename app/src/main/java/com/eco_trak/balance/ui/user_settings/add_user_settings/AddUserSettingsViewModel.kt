package com.eco_trak.balance.ui.user_settings.add_user_settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eco_trak.balance.data.db.user_settings.UserSettings
import com.eco_trak.balance.repo.user_settings.UserSettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddUserSettingsViewModel @Inject constructor(
    private val repo: UserSettingsRepository
) : ViewModel() {

    var userSettings by mutableStateOf(
        UserSettings(
            false,
            Date(),
            "",
            "",
            ""
        )
    )
        private set

    fun updateEnableVehicleManagement(enableVehicleManagement: Boolean) {
        userSettings = userSettings.copy(enableVehicleManagement = enableVehicleManagement)
    }

    fun updateDateTime(dateTime: String) {
        userSettings = userSettings.copy(dateTime = SimpleDateFormat("yyyy-MM-dd").parse(dateTime))
    }
    fun updateLanguage(language: String) {
        userSettings = userSettings.copy(language = language)
    }

    fun updateCompanyInfo(companyInfo: String) {
        userSettings = userSettings.copy(companyInfo = companyInfo)
    }

    fun updateCouponNumber(couponNumber: String) {
        userSettings = userSettings.copy(couponNumber = couponNumber)
    }

    fun addUserSettings(userId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUserSettingsFromRoom(userSettings.copy(id = userId))
        }
    }
}
