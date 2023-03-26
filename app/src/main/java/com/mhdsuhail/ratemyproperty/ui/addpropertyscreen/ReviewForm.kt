package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.data.Address
import com.mhdsuhail.ratemyproperty.data.Contributor
import com.mhdsuhail.ratemyproperty.data.PropertyDescription
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.PropertyView
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun PreviewReviewForm() {
    RateMyPropertyTheme() {


        ReviewForm(
            viewModel = AddPropertyScreenViewModel(
                assetRepository = PreviewAssetRepository(),
                propertyRepository = PreviewPropertyRepository(),
            )
        )
    }
}

// TODO: Form states and the entity models are not matching which is causing a lot of
//  unnecessary mapping in the UI and view-models

@Composable
fun ReviewForm(modifier: Modifier = Modifier, viewModel: AddPropertyScreenViewModel) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier
            .fillMaxSize(),
    ) { padding ->
        PropertyView(
            modifier = modifier.padding(padding),
            propertyDetails = PropertyDetails(
                imagePropertyUri = null,
                favourite = false,
                recentlyViewed = true,
                uri = "draft_1",
                price = 0,
                currency = "$",
                address = Address(
                    country = viewModel.addressFormState.country.value,
                    postalCode = viewModel.addressFormState.postalCode.value,
                    province = viewModel.addressFormState.province.value,
                    unitNum = viewModel.addressFormState.unitNum.value,
                    city = viewModel.addressFormState.city.value,
                    street = viewModel.addressFormState.street.value
                ),
                contributor = Contributor(
                    id = "",
                    name = viewModel.posterContact.name.value,
                    title = viewModel.posterContact.title.value,
                    phoneNumber = viewModel.posterContact.phoneNumber.value,
                    imageContributorUri = viewModel.posterContact.imageResourceId.value
                )
            ),
            features = viewModel.featuresListState,
            propertyDescription = PropertyDescription(
                prop_uri = "",
                text = viewModel.descriptionFormState.text.value
            )
        )
    }

}