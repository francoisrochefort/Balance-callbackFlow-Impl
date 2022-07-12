package com.eco_trak.balance.ui.components

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun DatePicker() {

    val calendar = Calendar.getInstance()
    calendar.time = Date()

    val date = remember { mutableStateOf("") }

    val datePickerDialog = DatePickerDialog(
        LocalContext.current,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            date.value = "$mDayOfMonth/${mMonth+1}/$mYear"
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.DAY_OF_MONTH),
        calendar.get(Calendar.YEAR)
    )
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                datePickerDialog.show()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0XFF0F9D58)
            )
        ) {
            Text(text = "Open Date Picker", color = Color.White)
        }
        Spacer(modifier = Modifier.size(100.dp))
        Text(text = "Selected Date: ${date.value}", fontSize = 30.sp, textAlign = TextAlign.Center)
    }
}