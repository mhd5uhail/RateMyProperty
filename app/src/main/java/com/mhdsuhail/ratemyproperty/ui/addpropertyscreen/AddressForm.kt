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
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun PreviewAddressForm(){
    RateMyPropertyTheme {
        AddressForm(viewModel = AddPropertyScreenViewModel(propertyRepository = FakePropertyRepository()))
    }
}

@Composable
fun AddressForm(modifier: Modifier = Modifier , viewModel: AddPropertyScreenViewModel = hiltViewModel()){
    val scaffoldState = rememberScaffoldState()

    val address = viewModel.addressFormState.value

    Scaffold(modifier = modifier, scaffoldState = scaffoldState) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)) {

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "Street") },
                value = address.street.value,
                onValueChange = {
                    address.street.value = it
                },
            )

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "City") },
                value = address.city.value,
                onValueChange = {
                    address.city.value = it
                },
            )

            OutlinedTextField(
                modifier = Modifier.padding(10.dp),
                label = { Text(text = "Country") },
                value = address.country.value,
                onValueChange = {
                    address.country.value = it
                },
            )



        }
    }



}