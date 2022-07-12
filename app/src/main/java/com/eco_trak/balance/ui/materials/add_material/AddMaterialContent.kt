package com.eco_trak.balance.ui.materials.add_material

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eco_trak.balance.ui.components.MyButton
import com.eco_trak.balance.ui.components.MyForm
import com.eco_trak.balance.ui.components.MyTextField
import com.eco_trak.balance.ui.components.TextDone
import com.eco_trak.balance.ui.theme.MyButtonColor1
import com.eco_trak.balance.ui.theme.MyButtonColor2

@Composable
fun AddMaterialContent(
    padding: PaddingValues,
    viewModel: AddMaterialViewModel = hiltViewModel()
) {
    MyForm {
        MyTextField(
            hint =  "Type a material name...",
            label = "Name",
            value = viewModel.material.name,
            onValueChange = { name -> viewModel.updateName(name) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.TextDone
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        MyButton(
            text = "Add",
            onClick = {
                viewModel.addMaterial()
            },
            colors = listOf(
                MyButtonColor1,
                MyButtonColor2
            )
        )
    }
}