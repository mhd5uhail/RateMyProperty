package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
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
import com.mhdsuhail.ratemyproperty.data.PosterContact
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.ContactCard
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.FeaturesList
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
                application = Application(),
            )
        )
    }
}


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
        bottomBar = {
            ContactCard(contactInfo = PosterContact(
                name = viewModel.posterContact.name.value,
                title = viewModel.posterContact.title.value,
                imageResourceId = null,
                phoneNumber = viewModel.posterContact.phoneNumber.value
            ),
                onCallClick = {
                    //viewModel.onEvent(PropertyScreenEvents.OnCallPosterClick(viewModel.state.value.propertyDetails.posterContact))
                }, onMessageClick = {
                    //viewModel.onEvent(PropertyScreenEvents.OnMessagePosterClick(viewModel.state.value.propertyDetails.posterContact))
                })
        }
    ) { padding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.propertyprop2),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(40.dp)),
                        contentScale = ContentScale.FillBounds
                    )

                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 1.dp)
                                .fillMaxWidth(),
                            text = "$ ${viewModel.price.value}",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryTextColor
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 1.dp)
                                .fillMaxWidth(0.85F),
                            text = "${viewModel.addressFormState.street.value} - ${viewModel.addressFormState.city.value}," + " ${viewModel.addressFormState.province.value}",
                            fontSize = 18.sp,
                            color = primaryTextColor
                        )

                    }
                    Divider(
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                        color = Color.LightGray, thickness = 2.dp
                    )

                    Column(
                        modifier = Modifier
                            .animateContentSize()
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        if (!showMoreState) {
                            Text(
                                text = viewModel.descriptionFormState.text.value
                                    ?: "No description provided",
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                color = Color.Gray
                            )
                        } else {
                            Text(
                                text = viewModel.descriptionFormState.text.value
                                    ?: "No description provided",
                                textAlign = TextAlign.Left,
                                color = Color.Gray
                            )
                        }

                        ClickableText(modifier = Modifier.align(Alignment.End),
                            text = AnnotatedString(
                                text = if (!showMoreState) {
                                    "Show more"
                                } else {
                                    "Show less"
                                },
                                spanStyle = SpanStyle(
                                    color = primaryTextColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp
                                )
                            ),
                            onClick = {
                                showMoreState = !showMoreState
                            })
                    }

                    Divider(
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                        color = Color.LightGray, thickness = 2.dp
                    )

                    FeaturesList(viewModel.featuresListState)
                }
            }
        }
    }

}