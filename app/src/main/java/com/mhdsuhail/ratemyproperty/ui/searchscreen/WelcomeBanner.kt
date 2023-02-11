package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor

@Composable
fun WelcomeBanner(){
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 5.dp, end = 5.dp, top = 5.dp)){
        Text(text = "Lets find you the best home !",
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = 30.sp,
            modifier = Modifier.fillMaxWidth())
    }
}