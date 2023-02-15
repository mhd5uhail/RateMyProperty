package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import com.mhdsuhail.ratemyproperty.data.Property

sealed class PropertyScreenEvents {

    data class OnCallPosterClick(val propertyUri: String) : PropertyScreenEvents()
    data class OnMessagePosterClick(val propertyUri: String) : PropertyScreenEvents()
    data class OnAddToFavouritesClick(val propertyUri: String, val isFav: Boolean ) : PropertyScreenEvents()

}
