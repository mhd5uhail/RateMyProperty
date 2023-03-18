package com.mhdsuhail.ratemyproperty.ui.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.ui.property.PropertyInfoCard
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.util.UiEvent
import java.time.LocalDateTime

@Preview
@Composable
fun HomeScreenPreview() {
    RateMyPropertyTheme {
        Surface() {
            HomeScreen(viewModel = HomeScreeViewModel(propertyRepository = PreviewPropertyRepository()),
                {})
        }
    }
}

@Composable
fun TitledLazyRowProperty(
    modifier: Modifier = Modifier,
    title: String,
    properties: List<PropertyDetails>,
    onClickItem: (propertyDetails:PropertyDetails)->Unit,
    onClickActionButton: (propertyDetails:PropertyDetails)->Unit
    ) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 20.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            items(properties) { property ->
                PropertyInfoCard(
                    modifier = Modifier.padding(10.dp),
                    propertyDetails = property,
                    onClickItem = {
                        onClickItem(it)
                    },
                    onClickActionButton = {
                        onClickActionButton(it)
                    })
            }

        }

    }
}

@Composable
fun PromptCard(modifier: Modifier = Modifier, imageVector: ImageVector, message: String) {
    Card(
        shape = RoundedCornerShape(10.dp), modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f), imageVector = imageVector, contentDescription = null
            )
            Box(modifier = Modifier
                .fillMaxSize()
                .weight(1f).padding(5.dp)) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = message,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryTextColor
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPromptCard() {
    RateMyPropertyTheme() {
        Surface() {
            PromptCard(imageVector = Icons.Default.List, message = "Add a property !")
        }
    }
}

@Composable
fun HomeScreen(
    viewModel: HomeScreeViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    val favList = viewModel.favoriteProperties.collectAsState(initial = emptyList())
    val myListings = viewModel.myListings.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = 1) {

        viewModel.uiEvent.collect() { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                is UiEvent.ShowSnackbar -> {
                    val result =
                        scaffoldState.snackbarHostState.showSnackbar(event.message, event.action)
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(HomeScreenEvents.OnClickUndoAddToFav)
                    }
                }
                else -> {}

            }
        }

    }
    Scaffold(
        scaffoldState = scaffoldState,
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            ArticleCard(
                modifier = Modifier.padding(10.dp),
                summary = stringResource(id = R.string.about_us),
                title = stringResource(id = R.string.about_us_title),
                date = LocalDateTime.now()
            )

            if (favList.value.isNotEmpty()) {
                TitledLazyRowProperty(
                    title = "Your Favorites",
                    properties = favList.value,
                    onClickActionButton = {
                        viewModel.onEvent(HomeScreenEvents.OnClickFavourite(it))
                    },
                    onClickItem = {
                        viewModel.onEvent(HomeScreenEvents.OnClickPropertyCard(it))
                    }
                )
            } else {
                // TODO: Prompt to add favourites
            }
            if (myListings.value.isNotEmpty()) {
                TitledLazyRowProperty(
                    title = "Your Listings",
                    properties = myListings.value,
                    onClickActionButton = {
                        viewModel.onEvent(HomeScreenEvents.OnClickFavourite(it))
                    },
                    onClickItem = {
                        viewModel.onEvent(HomeScreenEvents.OnClickPropertyCard(it))
                    }
                )
            } else {
                // TODO: Prompt to add property 
            }
        }
    }
}