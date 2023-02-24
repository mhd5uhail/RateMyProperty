package com.mhdsuhail.ratemyproperty.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreeViewModel @Inject constructor(private val propertyRepository: PropertyRepository) :
    ViewModel() {


    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    val favoriteProperties = propertyRepository.getFavouriteProperties()
    val myListings = propertyRepository.getProperties()
    private var removedPropertyDetails: PropertyDetails? = null

    fun onEvent(event: HomeScreenEvents) {

        when (event) {

            is HomeScreenEvents.OnClickFavourite -> {
                viewModelScope.launch {
                    var favstate = false
                    if (!event.propertyDetails.favourite) {
                        favstate = true
                    }
                    propertyRepository.updateProperty(event.propertyDetails.copy(favourite = favstate))
                    var actionSting: String? = null
                    var uiMessage: String = ""
                    if (favstate) {
                        uiMessage = "Added to"
                    } else {
                        uiMessage = "Removed from"
                        actionSting = "UNDO"
                    }
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = " $uiMessage favourites",
                            action = actionSting
                        )
                    )
                }
            }

            is HomeScreenEvents.OnClickPropertyCard -> {
                sendUiEvent(UiEvent.Navigate(Routes.PROP_VIEW_PAGE + "?prop_uri=${event.propertyDetails.uri}"))
            }

            is HomeScreenEvents.OnClickUndoAddToFav -> {
                viewModelScope.launch {
                    removedPropertyDetails?.let {
                        propertyRepository.updateProperty(it.copy(favourite = true))
                    }
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}


