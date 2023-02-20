package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor

@Preview
@Composable
fun SearchCardsPreview(){
    RateMyPropertyTheme {
        Surface() {
            SearchCards(prevQuery = "Houses in toronto")
        }
    }
}


@Composable
fun SearchCards(modifier: Modifier = Modifier,prevQuery: String) {
    Row(modifier = modifier
        .fillMaxWidth()
        .wrapContentHeight(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Start) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = prevQuery,
            fontSize = 18.sp,
            color = primaryTextColor,
        )
    }
}