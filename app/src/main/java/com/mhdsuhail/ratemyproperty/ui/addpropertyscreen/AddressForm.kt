package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
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
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.json.AssetJsonParserImpl
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.ui.globalui.OutlinedDropDown
import com.mhdsuhail.ratemyproperty.ui.globalui.OutlinedDropDownWFilter
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun PreviewAddressForm() {
    RateMyPropertyTheme {
        AddressForm(
            viewModel = AddPropertyScreenViewModel(
                propertyRepository = PreviewPropertyRepository(),
                application = Application(),
                assetRepository = PreviewAssetRepository()
            ),
        )
    }
}

@Composable
fun AddressSection(
    modifier: Modifier = Modifier,
    address: FormStates.Address,
    listOfProvinces: List<String>,
    mapOfCities: HashMap<String, List<String>>
) {
    val countryDropDownExpanded = remember {
        mutableStateOf(false)
    }
    val cityDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        TitleText(text = stringResource(id = R.string.address))


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

        OutlinedDropDown(
            expanded = countryDropDownExpanded,
            label = "Province",
            dropDownList = listOfProvinces,
            text = address.province,
            onSelectItem = {
                address.city.value = ""
            }
        )

        OutlinedDropDownWFilter(
            expanded = cityDropDownExpanded,
            label = "City",
            dropDownList = mapOfCities[address.province.value],
            text = address.city
        )

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
                if (it.length <= phoneNumberLength)
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
    viewModel: AddPropertyScreenViewModel
) {
    val scaffoldState = rememberScaffoldState()

    val address = remember {
        viewModel.addressFormState
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


            AddressSection(
                address = address,
                listOfProvinces = viewModel.province2City.keys.toList(),
                mapOfCities = viewModel.province2City
            )
            PosterSection(posterContact = posterContact)

        }
    }


}