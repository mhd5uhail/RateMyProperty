package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import com.mhdsuhail.ratemyproperty.data.PosterContact

sealed class PropertyScreenEvents {

    data class OnCallPosterClick(val posterContact: PosterContact) : PropertyScreenEvents()
    data class OnMessagePosterClick(val posterContact: PosterContact) : PropertyScreenEvents()
    data class OnAddToFavouritesClick(val propertyUri: String, val isFav: Boolean ) : PropertyScreenEvents()
    object OnBackButtonClick: PropertyScreenEvents()
    object OnClickShowMore: PropertyScreenEvents()
}
