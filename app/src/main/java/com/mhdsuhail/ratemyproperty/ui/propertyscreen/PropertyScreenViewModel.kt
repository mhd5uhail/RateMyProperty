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
    private val repository: PropertyWithExtraInfoRepo,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var property by mutableStateOf<Property?>(null)
    private  set
    var isfav by mutableStateOf(false)
    private set
    var uri by mutableStateOf("")
    private set
    var currency by mutableStateOf(" ")
    private  set
    var price by mutableStateOf(0)
    private  set
    var address by mutableStateOf(PlaceHolderValues().address)
        private set

    var features by mutableStateOf<List<Feature>>(ArrayList())
        private set

    var posterContact by mutableStateOf(PlaceHolderValues().posterContact)
        private set

    init {
        val propUri = savedStateHandle.get<String>("prop_uri")
        if (!propUri.isNullOrEmpty()) {
            viewModelScope.launch {
                repository.getPropertyWithExtraInfoById(propUri)?.let { property ->
                    address = property.property.address
                    features = property.features
                    posterContact = property.property.posterContact
                    currency = property.property.currency
                    price = property.property.price
                    isfav = property.property.favourite
                    uri = property.property.uri
                    this@PropertyScreenViewModel.property = property.property
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


data class PlaceHolderValues(
    val address: Address = Address(
        "Country",
        "Province",
        "City",
        "street",
        "#",
        "A#B #C#"
    ), val posterContact: PosterContact = PosterContact(
        "Name",
        "Title", null, "###-###-###"
    )
)