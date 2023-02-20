package com.mhdsuhail.ratemyproperty.ui.favouritescreen

import com.mhdsuhail.ratemyproperty.data.Property

sealed class FavouriteScreenEvents   {
    data class OnPropertyCardClick(val property: Property) : FavouriteScreenEvents()
    data class OnRemoveFromFavClick(val property: Property) : FavouriteScreenEvents()
    data class OnUndoRemoveFromFav(val property: Property): FavouriteScreenEvents()
}