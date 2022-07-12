package com.eco_trak.balance.ui.users.add_user

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.ui.components.*
import com.eco_trak.balance.ui.theme.MyButtonColor1
import com.eco_trak.balance.ui.theme.MyButtonColor2

@Composable
fun AddUserContent(
    padding: PaddingValues,
    viewModel: AddUserViewModel = hiltViewModel()
) {
    MyForm {
        MyTextField(
            hint =  "Type a user name...",
            label = "Name",
            value = viewModel.user.name,
            onValueChange = { name -> viewModel.updateName(name) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextNext
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyTextField(
            hint =  "Type a user password...",
            label = "Password",
            value = viewModel.user.password,
            onValueChange = { password -> viewModel.updatePassword(password) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.PasswordDone
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyButton(
            text = "Add",
            onClick = {
                viewModel.addUser()
            },
            colors = listOf(
                MyButtonColor1,
                MyButtonColor2
            )
        )
    }
}