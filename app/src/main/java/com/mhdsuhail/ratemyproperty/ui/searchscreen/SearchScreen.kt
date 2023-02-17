package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.layout.*
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepo
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.UiEvent
import kotlinx.coroutines.flow.collect

@Preview
@Composable
fun SearchScreenPreview() {
    RateMyPropertyTheme() {
        SearchScreen(
            viewModel = SearchScreenViewModel(propertyRepository = FakePropertyRepo()),
            PropertyPreviewParameterProvider().values.toList(),
            onNavigate = {})
    }
}


//@Preview
//@Composable
//fun TopActionBarPreview() {
//    RateMyPropertyTheme {
//        TopActionBar()
//    }
//}

@Composable
fun SearchScreen(
    viewModel: SearchScreenViewModel = hiltViewModel(),
    recentlyViewedProperties: List<Property>,
    onNavigate: (UiEvent.Navigate) -> Unit
) {

    LaunchedEffect(key1 = 1){

        viewModel.uiEvent.collect(){ event ->

            when(event){

                is UiEvent.PopBackStack -> {

                }

                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                is UiEvent.ShowSnackbar -> {

                }
            }


        }

    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        SearchBar()
        /** TODO: When a property is searched for (type something and press search
         * PropertyList gets populated with the search results and the
         * recent searches Text changes to a search filter with several options
         **/
        Text(
            text = "Recent searches",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 10.dp, start = 20.dp, bottom = 10.dp)
        )
        PropertyList(listOfProperties = recentlyViewedProperties, onItemClicked = {
            //Call to search screen viewmodel onPropertyClicked
        })
    }


}