package com.eco_trak.balance.ui.user_settings.update_user_settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.ui.components.*
import com.eco_trak.balance.ui.theme.MyButtonColor1
import com.eco_trak.balance.ui.theme.MyButtonColor2

@Composable
fun UpdateUserSettingsContent(
    padding: PaddingValues,
    id: Int,
    navigateToUsersScreen: () -> Unit,
    viewModel: UpdateUserSettingsViewModel = hiltViewModel()
) {
    MyForm {
        MyTextField(
            hint =  "Enable vehicle management...",
            label = "Enable vehicle management",
            value = viewModel.userSettings.enableVehicleManagement.toString(),
            onValueChange = { enableVehicleManagement ->
                viewModel.updateEnableVehicleManagement(enableVehicleManagement.toBoolean())
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextNext
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyTextField(
            hint =  "Date time...",
            label = "Date",
            value = viewModel.userSettings.dateTime.toString(),
            onValueChange = { dateTime -> viewModel.updateDateTime(dateTime) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextNext
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyTextField(
            hint =  "Type the language...",
            label = "Language",
            value = viewModel.userSettings.language,
            onValueChange = { language -> viewModel.updateLanguage(language) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextNext
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyTextField(
            hint =  "Type company information...",
            label = "Company",
            value = viewModel.userSettings.companyInfo,
            onValueChange = { companyInfo -> viewModel.updateCompanyInfo(companyInfo) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextNext
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyTextField(
            hint =  "Type a coupon number...",
            label = "Coupon Number",
            value = viewModel.userSettings.couponNumber,
            onValueChange = { couponNumber -> viewModel.updateCouponNumber(couponNumber) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextDone
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyButton(
            text = "Update",
            onClick = {
                viewModel.updateUserSettings()
                navigateToUsersScreen()
            },
            colors = listOf(
                MyButtonColor1,
                MyButtonColor2
            )
        )
    }
}