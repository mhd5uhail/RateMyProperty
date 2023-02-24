package com.mhdsuhail.ratemyproperty.ui.homescreen

import com.mhdsuhail.ratemyproperty.data.PropertyDetails

sealed class HomeScreenEvents{
    data class OnClickPropertyCard(val propertyDetails: PropertyDetails) : HomeScreenEvents()
    data class OnClickFavourite(val propertyDetails: PropertyDetails) : HomeScreenEvents()
    object OnClickUndoAddToFav: HomeScreenEvents()
}
