package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText


@Composable
fun PictureDescriptionForm(modifier : Modifier = Modifier){

    Column(modifier = modifier.fillMaxSize()) {
        TitleText(text = stringResource(id = R.string.description))
    }
}