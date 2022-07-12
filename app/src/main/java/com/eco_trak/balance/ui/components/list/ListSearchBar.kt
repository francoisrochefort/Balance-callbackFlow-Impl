package com.eco_trak.balance.ui.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.eco_trak.balance.ui.components.MyEditText

@Composable
fun ListSearchBar(
    padding: PaddingValues,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(padding)
    ) {
        MyEditText(
            caption = "Name",
            text = text,
            onTextChange = onTextChange,
            keyboardType = KeyboardType.Text,
            modifier = Modifier.fillMaxWidth()
        )
    }
}