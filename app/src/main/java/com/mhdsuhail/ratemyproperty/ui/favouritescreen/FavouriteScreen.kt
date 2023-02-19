package com.mhdsuhail.ratemyproperty.ui.favouritescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FavouriteScreen() {

    Scaffold() { padding->

        Column(modifier = Modifier.padding(padding)) {
            Text(text = "Coming Soon!")
        }
    }
}