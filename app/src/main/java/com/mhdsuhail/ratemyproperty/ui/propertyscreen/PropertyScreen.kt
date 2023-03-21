package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.preview.*
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import com.mhdsuhail.ratemyproperty.util.UiEvent

@Preview
@Composable
fun PropertyScreenPreviews() {
    RateMyPropertyTheme() {
        val viewModel = PropertyScreenViewModel(
            savedStateHandle = SavedStateHandle(),
            repository = PreviewPropertyRepository()
        )
        PropertyScreen(
            onNavigate = {},
            viewModel = viewModel,
            onPopBackStack = {}
        )
    }
}

@Composable
// Callback navigation function
fun PropertyScreen(
    modifier: Modifier = Modifier,
    onPopBackStack: () -> Unit,
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: PropertyScreenViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }

                is UiEvent.PopBackStack -> onPopBackStack()

            }

        }

    }
    PropertyView(
        modifier = modifier,
        scaffoldState = scaffoldState,
        propertyDetails = viewModel.state.value.propertyDetails,
        features = viewModel.state.value.features,
        propertyDescription = viewModel.state.value.description,
        posterContact = viewModel.state.value.propertyDetails.posterContact,
        onBackPressed = { /*TODO*/ },
        onFavouritePressed = { },
        bottomActionBar = {} // TODO: Convert this to an add review bar making it easy for users to quickly review
    )

}

@Composable
fun PropertyView(
    scaffoldState: ScaffoldState,
    modifier: Modifier = Modifier,
    propertyDetails: PropertyDetails,
    features: List<Feature>,
    propertyDescription: PropertyDescription,
    onBackPressed: () -> Unit,
    onFavouritePressed: (prop_id: String) -> Unit,
    bottomActionBar: @Composable() () -> Unit,
    posterContact: PosterContact,
    isPreview: Boolean = false
) {
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier
            .fillMaxSize(),
        bottomBar = { bottomActionBar() }
    ) { padding ->

        var showMoreState by remember {
            mutableStateOf(false)
        }
        Column(
            modifier = modifier
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
                    if (!isPreview) {
                        IconButton(
                            onClick = {
                                onFavouritePressed(propertyDetails.uri)
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-10).dp, y = 10.dp)
                                .clip(CircleShape)
                                .size(60.dp)
                                .background(Color.White.copy(alpha = 0.2f))
                        ) {
                            Icon(
                                imageVector = if (propertyDetails.favourite) {
                                    Icons.Rounded.Favorite
                                } else {
                                    Icons.Rounded.FavoriteBorder
                                },
                                modifier = Modifier.size(30.dp),
                                contentDescription = "Add to Favourites",
                                tint = Color.White
                            )
                        }

                        IconButton(
                            onClick = {
                                onBackPressed()
                            },
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .offset(x = (10).dp, y = 10.dp)
                                .clip(CircleShape)
                                .size(60.dp)
                                .background(Color.White.copy(alpha = 0.2f))
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                modifier = Modifier.size(30.dp),
                                contentDescription = "Go back to previous page",
                                tint = Color.White
                            )
                        }
                    }
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
                            text = propertyDetails.currency + propertyDetails.price,
                            fontSize = 35.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryTextColor
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 1.dp)
                                .fillMaxWidth(0.85F),
                            text = "${propertyDetails.address.street} - ${propertyDetails.address.city}," + " ${propertyDetails.address.province}",
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
                        if (showMoreState) {
                            Text(
                                text = let {
                                    propertyDescription.text.ifEmpty { "No description provided" }
                                },
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                color = Color.Gray
                            )
                        } else {
                            Text(
                                text = let {
                                    propertyDescription.text.ifEmpty { "No description provided" }
                                },
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

                    FeaturesList(features)
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun ContactCardPreview(@PreviewParameter(PosterContactPreviewProvider::class) contactInfo: PosterContact) {
//    RateMyPropertyTheme() {
//        Surface {
//            ContactCard(contactInfo)
//        }
//    }
//}

@Composable
fun ContactCard(
    contactInfo: PosterContact,
    onCallClick: () -> Unit,
    onMessageClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(35.dp), modifier = Modifier
            .fillMaxWidth()
            .height(85.dp),
        border = BorderStroke(width = 1.dp, color = LightGray),
        backgroundColor = Color.White
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f)
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(64.dp)
                        .clip(CircleShape), // Descriptive Image
                    painter = painterResource(
                        id = contactInfo.imageResourceId ?: R.drawable.contact
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Realtor Contact Picture"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.4f)
                    .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("${contactInfo.title}\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = primaryTextColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(contactInfo.name)
                        }
                    },
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.4f)
                    .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                ContactActionButton(modifier = Modifier.padding(start = 10.dp),
                    imageResourceId = R.drawable.chat,
                    backgroundColor = Color.Blue.copy(alpha = 0.15f),
                    tintColor = Color.Blue,
                    clickHandler = {
                        onMessageClick()
                    })

                ContactActionButton(modifier = Modifier.padding(start = 10.dp),
                    imageResourceId = R.drawable.phone,
                    backgroundColor = Color.Green.copy(alpha = 0.15f),
                    tintColor = Color.Green,
                    clickHandler = { onCallClick() })

            }

        }
    }
}

@Composable
fun ContactActionButton(
    modifier: Modifier = Modifier, imageResourceId: Int,
    backgroundColor: Color, tintColor: Color, clickHandler: () -> Unit,
    contentDescription: String? = null
) {
    IconButton(
        onClick = clickHandler,
        modifier = modifier
            .clip(CircleShape)
            .size(55.dp)
            .background(backgroundColor)
    ) {
        Icon(
            painter = painterResource(id = imageResourceId),
            modifier = Modifier.size(30.dp),
            contentDescription = contentDescription,
            tint = tintColor
        )
    }
}


@Composable
fun FeaturesList(features: List<Feature>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 1.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.feature_text),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = primaryTextColor
        )
        features.forEach { feature ->
            FeatureItem(feature)
        }
    }
}

@Preview
@Composable
fun FeatureItemPreviews(@PreviewParameter(PreviewFeatureProvider::class) feature: Feature) {
    RateMyPropertyTheme() {
        Surface() {
            FeatureItem(feature)
        }
    }
}

@Composable
fun FeatureItem(feature: Feature) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxHeight()
                .width(30.dp), // Descriptive Image
            painter = painterResource(id = feature.imageResource ?: R.drawable.square_foot),
            contentDescription = feature.description
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            text = feature.name, // Type
            color = Color.Gray
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(10.dp),
                text = "${feature.value} ${feature.unit}", // Value + unit
                fontWeight = FontWeight.SemiBold,
                color = primaryTextColor
            )
        }

    }
}