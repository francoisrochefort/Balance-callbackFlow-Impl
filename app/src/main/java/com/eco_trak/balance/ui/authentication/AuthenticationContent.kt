package com.eco_trak.balance.ui.authentication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.ui.components.MyButton
import com.eco_trak.balance.ui.components.MyForm
import com.eco_trak.balance.ui.components.MyTextField
import com.eco_trak.balance.ui.components.PasswordDone
import com.eco_trak.balance.ui.theme.MyButtonColor1
import com.eco_trak.balance.ui.theme.MyButtonColor2

@Composable
fun AuthenticationContent(
    id: Int,
    padding: PaddingValues,
    navigateToUsersScreens: () -> Unit,
    navigateToUpdateUserSettings: (id: Int) -> Unit,
    viewModel: AuthenticateViewModel = hiltViewModel()
) {
    var password by remember { mutableStateOf("") }

    MyForm {
        MyTextField(
            hint =  "Type a user password...",
            label = "Password",
            value = password,
            onValueChange = { text -> password = text },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.PasswordDone
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyButton(
            text = "Authenticate",
            onClick = {
                viewModel.authenticate()
                navigateToUpdateUserSettings(id)
            },
            colors = listOf(
                MyButtonColor1,
                MyButtonColor2
            )
        )
    }
}