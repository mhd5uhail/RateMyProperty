package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.location.Address
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    val scaffoldState = rememberScaffoldState()

    Scaffold(modifier = modifier, scaffoldState = scaffoldState) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)) {

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "Street") },
                value = "350-Columbia St W",
                onValueChange = {

                },
            )



        }
    }



}