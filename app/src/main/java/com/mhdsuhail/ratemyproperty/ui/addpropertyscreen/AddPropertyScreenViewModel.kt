package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.graphics.Picture
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
class AddPropertyScreenViewModel @Inject constructor(private val propertyRepository: PropertyRepository) :
    ViewModel() {

    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    lateinit var addressFormState: Address
    lateinit var posterContact: PosterContact
    var price: Int = 0
    lateinit var featuresList: List<Feature>
    lateinit var pictureData: Picture
    lateinit var description: PropertyDescription

    fun onEvent(event: AddPropertyScreenEvents) {

        when (event) {

            is AddPropertyScreenEvents.OnClickBack -> {
                sendUiEvent(UiEvent.PopBackStack)
            }

            is AddPropertyScreenEvents.OnClickSubmitForm -> {
                // todo: Send api call to server and then once successful store offline with received URI
                viewModelScope.launch {
                    val res = propertyRepository.insertPropertyDetails(
                        PropertyDetails(
                            uri = "newProp",
                            price = price,
                            currency = "$",
                            favourite = false,
                            recentlyViewed = false,
                            imageResourceId = null,
                            address = addressFormState,
                            posterContact = posterContact
                        )
                    )
                    val message = let{
                        if(res > 0)
                            "Property created :)"
                        else
                            "Failed to add x( "

                    }
                    sendUiEvent(UiEvent.ShowSnackbar(message))
                }
            }

            is AddPropertyScreenEvents.OnClickSubmitPage -> {
                when(event.page.route){

                    AddFormPages.AddressForm.route -> {
                        // todo: Create and prepare the address object
                    }
                    AddFormPages.AmenitiesForm.route -> {

                    }
                    AddFormPages.PictureDescForm.route -> {

                    }
                    AddFormPages.ReviewForm.route -> {
                        // On submitting the final review page form is completed
                        onEvent(AddPropertyScreenEvents.OnClickSubmitForm)
                    }

                }
            }

            else -> {}
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}