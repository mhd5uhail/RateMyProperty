package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import com.mhdsuhail.ratemyproperty.data.Contributor

sealed class PropertyScreenEvents {

    data class OnCallPosterClick(val contributor: Contributor) : PropertyScreenEvents()
    data class OnMessagePosterClick(val contributor: Contributor) : PropertyScreenEvents()
    data class OnAddToFavouritesClick(val propertyUri: String) : PropertyScreenEvents()
    object OnBackButtonClick: PropertyScreenEvents()
}
