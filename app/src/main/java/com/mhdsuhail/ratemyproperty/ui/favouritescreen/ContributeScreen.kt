package com.mhdsuhail.ratemyproperty.ui.favouritescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.UiEvent


@Preview
@Composable
fun PreviewContributeScreen(){
    RateMyPropertyTheme() {
        Surface {
            ContributeScreen(onNavigate = {})
        }
    }
}

@Composable
fun ContributeScreen(onNavigate: (UiEvent.Navigate) -> Unit){
    Column(modifier = Modifier.fillMaxSize()) {

    }
}