package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.mhdsuhail.ratemyproperty.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun CategoriesPreview() {
    RateMyPropertyTheme() {
        androidx.compose.material.Surface {
            Categories()
        }
    }
}

@Preview
@Composable
fun CategoryCardPreview(){

}

@Composable
fun Categories(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Card(){

        }


    }
}