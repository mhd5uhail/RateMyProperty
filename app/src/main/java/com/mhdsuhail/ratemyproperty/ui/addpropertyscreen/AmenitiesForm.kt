package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Composable
fun AmenitiesForm(modifier: Modifier= Modifier ){

    Column(modifier = modifier.fillMaxSize()) {
        TitleText(text = stringResource(id = R.string.amenities))
    }
}

@Preview
@Composable
fun PreviewAmenitiesForm(){
    RateMyPropertyTheme {
        Surface() {
            AmenitiesForm()
        }
    }
}