package com.mhdsuhail.ratemyproperty.ui.homescreen

import com.mhdsuhail.ratemyproperty.data.Property

sealed class HomeScreenEvents{
    data class OnClickPropertyCard(val property: Property) : HomeScreenEvents()
    data class OnClickFavourite(val property: Property) : HomeScreenEvents()
    object OnClickUndoAddToFav: HomeScreenEvents()
}
