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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import coil.compose.AsyncImage
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.preview.*
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText
import com.mhdsuhail.ratemyproperty.ui.theme.*
import com.mhdsuhail.ratemyproperty.util.UiEvent

@Preview
@Composable
fun PropertyScreenPreviews() {
    RateMyPropertyTheme {
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
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = modifier
            .fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .wrapContentSize()
        ) {
            PropertyView(
                propertyDetails = viewModel.state.value.propertyDetails,
                features = viewModel.state.value.features,
                propertyDescription = viewModel.state.value.description,
                onBackPressed = { viewModel.onEvent(PropertyScreenEvents.OnBackButtonClick) },
                onFavouritePressed = { property ->
                    viewModel.onEvent(PropertyScreenEvents.OnAddToFavouritesClick(property))
                },
            )
            TitleText(text = stringResource(id = R.string.contributor))
            ContributorCard(contributor = viewModel.state.value.propertyDetails.contributor,
                onUpVote = {},
                onDownVote = {})
        }
    }
}

@Composable
fun PropertyView(
    modifier: Modifier = Modifier,
    propertyDetails: PropertyDetails,
    features: List<Feature>,
    propertyDescription: PropertyDescription,
    onBackPressed: (() -> Unit)? = null,
    onFavouritePressed: ((prop_id: String) -> Unit)? = null,
) {

    var showMoreState by remember {
        mutableStateOf(false)
    }
    var textOverflow by remember {
        mutableStateOf(false)
    }
    val maxDescPreviewLineCount = 4;

    Column(
        modifier = modifier
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
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(40.dp)),
                    model = propertyDetails.imagePropertyUri,
                    contentDescription = "property Image",
                    contentScale = ContentScale.FillBounds
                )
                onFavouritePressed?.let {
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
                }
                onBackPressed?.let {
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
                        text = "${propertyDetails.address.street}\n" +
                                " ${propertyDetails.address.city}," + " ${propertyDetails.address.province}",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.SemiBold,
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

                    Text(
                        text = let {
                            propertyDescription.text.ifEmpty { "No description provided" }
                        },
                        maxLines = let { if (showMoreState) Int.MAX_VALUE else maxDescPreviewLineCount },
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Left,
                        color = Color.Gray,
                        onTextLayout = { textOverflow = it.hasVisualOverflow }
                    )

                    if(textOverflow) {
                        ClickableText(modifier = Modifier.align(Alignment.End),
                            text = AnnotatedString(
                                text = "Show more",
                                spanStyle = SpanStyle(
                                    color = primaryTextColor,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 17.sp,
                                    textDecoration = TextDecoration.Underline
                                )
                            ),
                            onClick = {
                                showMoreState = !showMoreState
                            })
                    }
                }

                Divider(
                    modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                    color = Color.LightGray, thickness = 2.dp
                )
                TitleText(text = stringResource(id = R.string.feature_text))
                FeaturesList(features)
            }
        }

    }
}


@Composable
fun ContributorCard(
    modifier: Modifier = Modifier,
    contributor: Contributor,
    onUpVote: () -> Unit,
    onDownVote: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        AsyncImage(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape), model = contributor.imageContributorUri,
            contentScale = ContentScale.Crop,
            contentDescription = "Realtor Contact Picture"
        )

        Row(
            modifier = Modifier
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = Color.Gray)) {
                        append("${contributor.title}\n")
                    }
                    withStyle(
                        style = SpanStyle(
                            color = primaryTextColor,
                            fontWeight = FontWeight.SemiBold
                        )
                    ) {
                        append(contributor.name)
                    }
                },
            )
        }

        Row(
            modifier = Modifier
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            IconButton(onClick = {
                onDownVote()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.downvote_24),
                    modifier = Modifier.size(55.dp),
                    contentDescription = "Down-vote Contributor",
                    tint = Color.Red.copy(alpha = 0.5f)
                )
            }
            Text(text = "999+", fontSize = 20.sp)
            IconButton(onClick = {
                onUpVote()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.upvote_24),
                    modifier = Modifier.size(55.dp),
                    contentDescription = "Up-vote Contributor",
                    tint = Color.Green.copy(alpha = 0.5f)
                )
            }

        }


    }
}

@Composable
fun ActionButton(
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
            modifier = Modifier.size(45.dp),
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
        features.forEach { feature ->
            FeatureItem(feature)
        }
    }
}

//@Preview
//@Composable
//fun FeatureItemPreviews(@PreviewParameter(PreviewFeatureProvider::class) feature: Feature) {
//    RateMyPropertyTheme {
//        Surface {
//            FeatureItem(feature)
//        }
//    }
//}

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