package com.mhdsuhail.ratemyproperty.ui.searchscreen

import com.mhdsuhail.ratemyproperty.data.Property

sealed class SearchScreenEvents {
    data class OnPropertyCardClick(val property: Property) : SearchScreenEvents()
    object OnSearchBarClick : SearchScreenEvents()
    data class OnAddToFavouritesClick(val property: Property) : SearchScreenEvents()
    data class OnSearchQueryChange(val query: String): SearchScreenEvents()
}
