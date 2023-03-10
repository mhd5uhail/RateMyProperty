package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
import android.location.Address
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.CanadianProvince
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewCanadianProvinceParser
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun PreviewAddressForm() {
    RateMyPropertyTheme {
        AddressForm(
            viewModel = AddPropertyScreenViewModel(
                propertyRepository = FakePropertyRepository(),
                canadianProvinceParser = PreviewCanadianProvinceParser(),
                application = Application(),
            ),
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddressSection(
    modifier: Modifier = Modifier,
    address: FormStates.Address,
    listOfProvinceCity: List<CanadianProvince>
) {
    var countryDropDownExpanded by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        TitleText(text = stringResource(id = R.string.about_you))


        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Apt/Unit #") },
            value = address.unitNum.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = {
                address.unitNum.value = it
            },
            singleLine = true
        )
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Street") },
            value = address.street.value,
            onValueChange = {
                address.street.value = it
            },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "City") },
            value = address.city.value,
            onValueChange = {
                address.city.value = it
            },
            singleLine = true
        )

        ExposedDropdownMenuBox(
            expanded = countryDropDownExpanded,
            onExpandedChange = { countryDropDownExpanded = !countryDropDownExpanded }) {

            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                label = { Text(text = "Province") },
                value = address.state.value,
                onValueChange = {
                    address.state.value = it
                },
                singleLine = true,
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = countryDropDownExpanded) }
            )

            ExposedDropdownMenu(
                expanded = countryDropDownExpanded,
                onDismissRequest = { countryDropDownExpanded = false }) {
                listOfProvinceCity.forEach { province ->

                    DropdownMenuItem(onClick = {
                        address.state.value = province.name.toString()
                        countryDropDownExpanded = false
                    }) {
                        Text(text = province.name.toString(), maxLines = 1)
                    }
                }


            }
        }


        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Postal Code") },
            value = address.postalCode.value,
            onValueChange = {
                address.postalCode.value = it
            },
        )
    }
}

@Composable
fun PosterSection(modifier: Modifier = Modifier, posterContact: FormStates.PosterContact) {
    val phoneNumberLength = 10

    Column(modifier = modifier) {
        TitleText(text = stringResource(id = R.string.about_you))

        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Name") },
            value = posterContact.name.value,
            onValueChange = {
                posterContact.name.value = it
            },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Title") },
            value = posterContact.title.value,
            onValueChange = {
                posterContact.title.value = it
            },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Mobile") },
            value = posterContact.phoneNumber.value,
            onValueChange = {
                if(it.length<=phoneNumberLength)
                    posterContact.phoneNumber.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true
        )


    }
}

@Composable
fun AddressForm(
    modifier: Modifier = Modifier,
    viewModel: AddPropertyScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    val address = remember {
        viewModel.addressFormState.value
    }
    val posterContact = remember {
        viewModel.posterContact.value
    }

    Scaffold(modifier = modifier, scaffoldState = scaffoldState) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState(), true)
        ) {


            AddressSection(address = address, listOfProvinceCity = viewModel.listOfProvince)
            PosterSection(posterContact = posterContact)

        }
    }


}