package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.graphics.Picture
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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

    var addressFormState: MutableState<FormStates.Address> = mutableStateOf(FormStates.Address())
    var posterContact: MutableState<FormStates.PosterContact> = mutableStateOf(FormStates.PosterContact())
    var price = mutableStateOf(0)
    lateinit var featuresList: MutableState<List<Feature>>
    lateinit var pictureData: MutableState<Picture>
    var description: MutableState<FormStates.PropertyDescription> = mutableStateOf(FormStates.PropertyDescription())


    fun onEvent(event: AddPropertyScreenEvents) {

        when (event) {

            is AddPropertyScreenEvents.OnClickBack -> {
                sendUiEvent(UiEvent.PopBackStack)
            }

            is AddPropertyScreenEvents.OnClickSubmitForm -> {
                // todo: Send api call to server and then once successful store offline with received URI
                viewModelScope.launch {
                    val address = addressFormState.value
                    val contact = posterContact.value
                    val res = propertyRepository.insertPropertyDetails(
                        PropertyDetails(
                            uri = "newProp",
                            price = price.value,
                            currency = "$",
                            favourite = false,
                            recentlyViewed = false,
                            imageResourceId = null,
                            address = Address(
                                country = address.country.value,
                                state = address.state.value,
                                city = address.city.value,
                                street = address.street.value,
                                unitNum = address.unitNum.value,
                                postalCode = address.postalCode.value
                            ),
                            posterContact = PosterContact(
                                name = contact.name.value,
                                title = contact.title.value,
                                imageResourceId = contact.imageResourceId.value,
                                phoneNumber =  contact.phoneNumber.value
                            )
                        )
                    )
                    val message = let {
                        if (res > 0)
                            "Property created :)"
                        else
                            "Failed to add x( "

                    }
                    sendUiEvent(UiEvent.ShowSnackbar(message))
                }
            }

            is AddPropertyScreenEvents.OnClickSubmitPage -> {
                when (event.page.route) {

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