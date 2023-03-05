package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.location.Address
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun PreviewAddressForm(){
    RateMyPropertyTheme {
        AddressForm()
    }
}

@Composable
fun AddressForm(modifier: Modifier = Modifier ){
    Column(Modifier.fillMaxSize()) {
        
    }

}