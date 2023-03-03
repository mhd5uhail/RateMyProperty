package com.mhdsuhail.ratemyproperty.ui.searchscreen

import com.mhdsuhail.ratemyproperty.data.PropertyDetails

sealed class SearchScreenEvents {
    data class OnPropertyCardClick(val propertyDetails: PropertyDetails) : SearchScreenEvents()
    object OnSearchBarClick : SearchScreenEvents()
    data class OnAddToFavouritesClick(val propertyDetails: PropertyDetails) : SearchScreenEvents()
    data class OnSearchQueryChange(val query: String): SearchScreenEvents()
}
