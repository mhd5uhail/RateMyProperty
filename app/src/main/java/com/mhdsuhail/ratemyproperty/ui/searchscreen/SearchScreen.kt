package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.data.preview.FakeSearchRepository
import com.mhdsuhail.ratemyproperty.ui.property.PropertyInfoCard
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.UiEvent

@Preview
@Composable
fun SearchScreenPreview() {
    RateMyPropertyTheme() {
        SearchScreen(
            viewModel = SearchScreenViewModel(
                propertyRepository = FakePropertyRepository(),
                searchHistoryRepository = FakeSearchRepository()
            ),
            onNavigate = {})
    }
}

@Preview
@Composable
fun PreviewSearchButton() {
    RateMyPropertyTheme {
        Surface {
            SearchButton {

            }
        }
    }
}

@Composable
fun SearchButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        onClick = { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color.Gray
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = "Where would you like to stay ?",
                color = Color.Gray,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val recentlyViewed = viewModel.recentlyViewed.collectAsState(initial = emptyList())


    LaunchedEffect(key1 = 1) {

        viewModel.uiEvent.collect() { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }
                else -> {}
            }
        }

    }
    Scaffold(scaffoldState = scaffoldState) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
        ) {

            SearchButton(modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                onClick = {
                    viewModel.onEvent(SearchScreenEvents.OnSearchBarClick)
                })

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text(
                    text = "Recently viewed",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    color = primaryTextColor,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 10.dp)
                )

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(recentlyViewed.value) { property ->
                        PropertyInfoCard(propertyDetails = property, onClickItem = {
                            // Launch the property page
                            viewModel.onEvent(SearchScreenEvents.OnPropertyCardClick(property))
                        }, onClickActionButton = {
                            viewModel.onEvent(SearchScreenEvents.OnAddToFavouritesClick(property))
                        })
                    }
                }
            }


        }
    }
}


