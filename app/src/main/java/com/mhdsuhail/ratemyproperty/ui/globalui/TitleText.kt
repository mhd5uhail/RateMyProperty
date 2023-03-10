package com.mhdsuhail.ratemyproperty.ui.globalui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor

@Composable
fun TitleText(text: String){
    Text(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        text = text,
        fontSize = 25.sp,
        fontWeight = FontWeight.SemiBold,
        color = primaryTextColor
    )
}