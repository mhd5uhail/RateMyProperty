package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepo
import com.mhdsuhail.ratemyproperty.data.preview.FakeSearchRepository
import com.mhdsuhail.ratemyproperty.ui.property.PropertyInfoCardX
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import com.mhdsuhail.ratemyproperty.ui.theme.textFieldBackGround
import com.mhdsuhail.ratemyproperty.util.UiEvent


@Preview
@Composable
fun DynamicSearchListPreview() {
    RateMyPropertyTheme() {
        Surface() {

            DynamicSearchList( viewModel = SearchScreenViewModel(
                propertyRepository = FakePropertyRepo(),
                searchHistoryRepository = FakeSearchRepository()
            ),
                onNavigate = {},
                onBackPressed = {}
            )
        }
    }
}


@Composable
fun DynamicSearchList(
    onBackPressed: () -> Unit,
    onNavigate: (UiEvent.Navigate) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel = hiltViewModel(),
) {

    LaunchedEffect(key1 = 2) {

        viewModel.uiEvent.collect { event ->

            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                is UiEvent.PopBackStack -> {
                    onBackPressed()
                }
                else -> {}
            }
        }

    }

    Column(
        modifier = modifier
            .animateContentSize()
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextField(
            placeholder = { Text(text = "Where would you like to stay ?") },
            value = viewModel.queryString.value,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = textFieldBackGround
            ),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            onValueChange = {
                viewModel.onEvent(SearchScreenEvents.OnSearchQueryChange(it))
            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            leadingIcon = {
                IconButton(onClick = { onBackPressed() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back to previous screen",
                    )
                }
            },
            singleLine = true,
            shape = RectangleShape
        )

        if(viewModel.queryString.value.isBlank() && viewModel.searchResults.isEmpty()){
            Text(
                text = "Try entering an address \n example: \"350 Columbia St W\"",
                textAlign = TextAlign.Center,
                color = primaryTextColor,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp, start = 20.dp, bottom = 10.dp)
            )
        } else if(viewModel.searchResults.isEmpty()){
            Text(
                text = "No properties found ;(",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 15.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp, start = 20.dp, bottom = 10.dp)
            )

        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.fillMaxSize()
            ) {
                items(viewModel.searchResults) { property ->
                    PropertyInfoCardX(property = property, onClickItem = {
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