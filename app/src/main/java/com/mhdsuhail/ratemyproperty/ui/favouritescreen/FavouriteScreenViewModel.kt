package com.mhdsuhail.ratemyproperty.ui.favouritescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteScreenViewModel @Inject constructor(private val propertyRepository: PropertyRepository) :
    ViewModel() {

    val favourites = propertyRepository.getFavouriteProperties()

    private val _uiEvents = Channel<UiEvent>()

    val uiEvent = _uiEvents.receiveAsFlow()

    private var removedProperty : Property? = null

    fun onEvent(event: FavouriteScreenEvents) {

        when (event) {

            is FavouriteScreenEvents.OnPropertyCardClick -> {
                sendUIEvent(UiEvent.Navigate(Routes.PROP_VIEW_PAGE + "?prop_uri=${event.property.uri}"))
            }

            is FavouriteScreenEvents.OnRemoveFromFavClick -> {
                viewModelScope.launch {
                    removedProperty = event.property
                    propertyRepository.updateProperty(event.property.copy(favourite = false))
                    sendUIEvent(UiEvent.ShowSnackbar("Removed from favourites","UNDO"))
                }
            }

            is FavouriteScreenEvents.OnUndoRemoveFromFav -> {
                viewModelScope.launch {
                    removedProperty?.let {
                        propertyRepository.updateProperty(it.copy(favourite = true))
                    }
                }
            }

        }

    }
    private fun sendUIEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }


}