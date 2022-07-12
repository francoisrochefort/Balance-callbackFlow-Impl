package com.eco_trak.balance.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eco_trak.balance.R
import com.eco_trak.balance.ui.theme.MyTextBorderColor
import com.eco_trak.balance.ui.theme.MyTextColor
import com.eco_trak.balance.ui.theme.MyTextHintColor

@Composable
fun MyText(
    hint: String,
    text: String,
    modifier: Modifier = Modifier,
    color: Color = MyTextColor,
    fontSize: TextUnit = TextUnit.Unspecified
) {
    Column(
        modifier = modifier
            .border(
                width = dimensionResource(id = R.dimen.MyTextBorderWidth),
                color = MyTextBorderColor,
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.MyTextBorderRadius))
            )
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 10.dp,
                    top = 5.dp
                ),
            text = text.uppercase(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
            color = color,
            fontSize = fontSize,
        )
        Text(
            modifier = Modifier
                .padding(
                    start = 10.dp,
                    bottom = 5.dp
                ),
            text = hint.uppercase(),
            fontSize = 8.sp,
            color = MyTextHintColor
        )
    }
}

@Preview
@Composable
fun MyTextPreview() {
    MyText(hint = "Hint", text = "Text")
}