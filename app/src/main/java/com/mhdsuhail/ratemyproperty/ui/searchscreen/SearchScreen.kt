package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepo
import com.mhdsuhail.ratemyproperty.ui.propertycard.PropertyInfoCard
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.textFieldBackGround
import com.mhdsuhail.ratemyproperty.util.UiEvent

@Preview
@Composable
fun SearchScreenPreview() {
    RateMyPropertyTheme() {
        SearchScreen(
            viewModel = SearchScreenViewModel(propertyRepository = FakePropertyRepo()),
            onNavigate = {})
    }
}

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val properties = viewModel.propertyList.collectAsState(initial = emptyList())
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
        
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
            ) {

                OutlinedTextField(
                    placeholder = { Text(text = "Where would you like to stay ?")},
                    value = viewModel.queryString.value,
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = textFieldBackGround
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    onValueChange = {
                        viewModel.onEvent(SearchScreenEvents.OnSearchQueryChange(it))
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                )

                Button(
                    onClick = { viewModel.onEvent(SearchScreenEvents.OnShowResultsClick(viewModel.queryString.value)) },
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        .height(60.dp)
                ) {
                    Text(text = "Show Results", fontSize = 15.sp)
                }
            }


            Text(
                text = "Recent searches",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 25.sp,
                modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 10.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(properties.value) { property ->
                        PropertyInfoCard(property = property, onClickItem = {
                            // Launch the property page
                            viewModel.onEvent(SearchScreenEvents.OnPropertyCardClick(property))
                        })
                    }
                }

            }
        }
    }


}