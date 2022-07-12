package com.eco_trak.balance.ui.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun MyTextField(
    hint: String,
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.TextNext,
    singleLine: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val focusManager = LocalFocusManager.current
    TextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = hint
            )
        },
        label = {
            Text(
                text = label
            )
        },
        singleLine = singleLine,
        modifier = modifier,
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
            },
            onNext = {
                focusManager.moveFocus(FocusDirection.Down)
            }
        ),
        keyboardOptions = keyboardOptions,
        maxLines = maxLines,
        visualTransformation = visualTransformation
    )
}

var KeyboardOptions.Companion.TextNext : KeyboardOptions
    get() = Default.copy(
        capitalization = KeyboardCapitalization.Characters,
        autoCorrect = false,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
    )
    set(value) = TODO()

var KeyboardOptions.Companion.TextDone : KeyboardOptions
    get() = Default.copy(
        capitalization = KeyboardCapitalization.Characters,
        autoCorrect = false,
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done,
    )
    set(value) = TODO()

var KeyboardOptions.Companion.NumberNext : KeyboardOptions
    get() = Default.copy(
        capitalization = KeyboardCapitalization.Characters,
        autoCorrect = false,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next,
    )
    set(value) = TODO()


var KeyboardOptions.Companion.NumberDone : KeyboardOptions
    get() = Default.copy(
        capitalization = KeyboardCapitalization.Characters,
        autoCorrect = false,
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Done,
    )
    set(value) = TODO()

var KeyboardOptions.Companion.PasswordDone : KeyboardOptions
    get() = Default.copy(
        capitalization = KeyboardCapitalization.None,
        autoCorrect = false,
        keyboardType = KeyboardType.Password,
        imeAction = ImeAction.Done,
    )
    set(value) = TODO()



