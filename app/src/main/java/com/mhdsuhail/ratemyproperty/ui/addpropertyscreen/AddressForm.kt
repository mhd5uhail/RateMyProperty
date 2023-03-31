package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
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
                assetRepository = PreviewAssetRepository()
            ),
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AddressSection(
    modifier: Modifier = Modifier,
    address: FormStates.Address,
    listOfProvinces: List<String>,
    mapOfCities: HashMap<String, List<String>>
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current

    val countryDropDownExpanded = remember {
        mutableStateOf(false)
    }
    val cityDropDownExpanded = remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier) {
        TitleText(text = stringResource(id = R.string.address))

        // Cannot be empty
        OutlinedTextField(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            label = { Text(text = "Apt/Unit #") },
            value = address.unitNum.value,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Number
            ),
            keyboardActions = KeyboardActions(onDone = {
                // move focus to the next

            }),
            onValueChange = {
                address.unitNum.value = it
            },
            singleLine = true,
            isError = !FieldValidators.Address.isUnitNumberValid(address.unitNum.value)
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
            singleLine = true,
            isError = !FieldValidators.Address.isStreetNameValid(address.street.value)
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
            isError = !FieldValidators.Address.isPostalCodeValid(address.postalCode.value),
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
        }
    }


}