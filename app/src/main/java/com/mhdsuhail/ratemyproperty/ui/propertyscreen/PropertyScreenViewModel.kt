package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.preview.PreviewContributorProvider
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyScreenViewModel @Inject constructor(
    private val repository: PropertyRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state =
        mutableStateOf(getEmptyProperty())

    init {
        val propUri = savedStateHandle.get<String>("prop_uri")
        if (!propUri.isNullOrEmpty()) {
            viewModelScope.launch {
                repository.getPropertyById(propUri)?.let { property ->
                    state.value = property
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
                state.value =
                    state.value.copy(propertyDetails = state.value.propertyDetails.copy(favourite = !state.value.propertyDetails.favourite))
                sendUiEvent(UiEvent.ShowSnackbar("Added to favourites !"))
            }
            is PropertyScreenEvents.OnCallPosterClick -> {
                sendUiEvent(UiEvent.ShowSnackbar("Calling ...${event.contributor.name}"))
            }
            is PropertyScreenEvents.OnMessagePosterClick -> {
                sendUiEvent(UiEvent.ShowSnackbar("Messaging ...${event.contributor.name}"))
            }
            is PropertyScreenEvents.OnBackButtonClick -> {
                sendUiEvent(UiEvent.PopBackStack)
            }
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private fun getEmptyProperty(): Property {
        return Property(
            propertyDetails = PropertyDetails(
                "", 0, "", false,
                favourite = false,
                imagePropertyUri = null,
                address = Address("", "", "", "", "", ""),
                contributor = Contributor("", "", "", null, "")
            ),
            features = emptyList(),
            description = PropertyDescription(prop_uri = "", text = "")
        )
    }

}