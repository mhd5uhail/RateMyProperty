package com.mhdsuhail.ratemyproperty.ui.favouritescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepo
import com.mhdsuhail.ratemyproperty.ui.propertycard.PropertyInfoCard
import com.mhdsuhail.ratemyproperty.ui.searchscreen.SearchScreenEvents
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.UiEvent
import kotlinx.coroutines.flow.collect

@Preview
@Composable
fun FavouriteScreenPreview() {
    RateMyPropertyTheme {
        FavouriteScreen(
            onNavigate = {  },
            viewModel = FavouriteScreenViewModel(FakePropertyRepo())
        )
    }
}

@Composable
fun FavouriteScreen(
    onNavigate: (route: UiEvent.Navigate) -> Unit,
    viewModel: FavouriteScreenViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val favProperties = viewModel.favourites.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = 1) {

        viewModel.uiEvent.collect() { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    onNavigate(event)
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message, event.action)
                }
                else -> {}

            }

        }

    }



    Scaffold(scaffoldState = scaffoldState) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                items(favProperties.value) { property ->
                    PropertyInfoCard(property = property,
                        actionImageVector = Icons.Default.Delete,
                        onClickItem = {
                        // Launch the property page
                        viewModel.onEvent(FavouriteScreenEvents.OnPropertyCardClick(property))
                    },
                    onClickActionButton = {
                        viewModel.onEvent(FavouriteScreenEvents.OnRemoveFromFavClick(property))
                    })
                }
            }
        }
    }
}