package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.preview.PropertyListProvider
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.propertycard.PropertyInfoCard
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun PropertyListPreview(@PreviewParameter(PropertyListProvider::class) properties: List<Property>) {
    RateMyPropertyTheme() {
        PropertyList(listOfProperties = properties)
    }
}

@Composable
fun PropertyList(listOfProperties: List<Property>,modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        LazyColumn(horizontalAlignment = Alignment.CenterHorizontally
            ,modifier = modifier.fillMaxSize()) {
            items(listOfProperties) { property ->
                PropertyInfoCard(property)
            }
        }

    }
}
