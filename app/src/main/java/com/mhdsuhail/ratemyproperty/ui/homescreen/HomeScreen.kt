package com.mhdsuhail.ratemyproperty.ui.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.data.preview.CategoryItemPreviewProvider
import com.mhdsuhail.ratemyproperty.ui.searchscreen.CategoryMatrix
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun HomeScreenPreview() {
    RateMyPropertyTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {

    Column(modifier = Modifier.fillMaxSize()) {
        CategoryMatrix(items = CategoryItemPreviewProvider().values.toList(),
            itemsPerRow = 2)
    }
}