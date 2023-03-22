package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Address
import com.mhdsuhail.ratemyproperty.data.PosterContact
import com.mhdsuhail.ratemyproperty.data.PropertyDescription
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.ContributorCard
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.FeaturesList
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.PropertyView
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor

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
    var showMoreState by remember {
        mutableStateOf(false)
    }

    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier
            .fillMaxSize(),
    ) { padding ->
        PropertyView(
            modifier = modifier.padding(padding),
            scaffoldState = scaffoldState,
            propertyDetails = PropertyDetails(
                posterContact = PosterContact(
                    name = viewModel.posterContact.name.value,
                    title = viewModel.posterContact.title.value,
                    phoneNumber = viewModel.posterContact.phoneNumber.value,
                    imageResourceId = R.drawable.sample_realtor_3
                ),
                imageResourceId = null,
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
                )
            ),
            features = viewModel.featuresListState,
            propertyDescription = PropertyDescription(
                prop_uri = "",
                text = viewModel.descriptionFormState.text.value
            ),
            posterContact = PosterContact(
                name = viewModel.posterContact.name.value,
                title = viewModel.posterContact.title.value,
                phoneNumber = viewModel.posterContact.phoneNumber.value,
                imageResourceId = R.drawable.sample_realtor_3

            ),
            isPreview = true
        )
    }

}