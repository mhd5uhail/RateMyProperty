package com.mhdsuhail.ratemyproperty.ui.searchscreen

import com.mhdsuhail.ratemyproperty.data.Property

sealed class SearchScreenEvents {
    data class OnPropertyCardClick(val property: Property) : SearchScreenEvents()
    data class OnShowResultsClick(val query: String) : SearchScreenEvents()
    data class OnAddToFavouritesClick(val property: Property) : SearchScreenEvents()
}
