package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.NavBarItem

class PreviewNavItemsProvider : PreviewParameterProvider<List<NavBarItem>> {
    override val values = sequenceOf(
        listOf(
            NavBarItem(R.drawable.home, "Home", "Home Page"),
            NavBarItem(R.drawable.search, "Search", "Search Page") ,
            NavBarItem(R.drawable.favourite, "Favourites", "Favourites Page"),
        )
    )
}