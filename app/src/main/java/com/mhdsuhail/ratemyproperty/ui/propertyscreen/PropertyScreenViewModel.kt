package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyScreenViewModel @Inject constructor(
    private val repository: PropertyWExtraInfoRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(PropertyScreenState())

    init {
        val propUri = savedStateHandle.get<String>("prop_uri")
        if (!propUri.isNullOrEmpty()) {
            viewModelScope.launch {
                repository.getPropertyWithExtraInfoById(propUri)?.let { property ->
                    state.address = property.property.address
                    state.features = property.features
                    state.posterContact = property.property.posterContact
                    state.currency = property.property.currency
                    state.price = property.property.price
                    state.isfav = property.property.favourite
                    state.uri = property.property.uri
                    state.description = property.description

                }
            }
        }
    }


    // Communication channel from UI to view-model
    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: PropertyScreenEvents) {

        when (event) {

            is PropertyScreenEvents.OnAddToFavouritesClick -> {
                sendUiEvent(UiEvent.ShowSnackbar("Added to favourites !"))
            }
            is PropertyScreenEvents.OnCallPosterClick -> {
                sendUiEvent(UiEvent.ShowSnackbar("Calling ..."))
            }
            is PropertyScreenEvents.OnMessagePosterClick -> {
                sendUiEvent(UiEvent.ShowSnackbar("Messaging ..."))
            }

        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}