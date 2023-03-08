package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
import android.graphics.Picture
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.json.JsonParser
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyScreenViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    canadianProvinceParser: JsonParser<CanadianProvince>,
    application: Application
) :
    AndroidViewModel(application) {

    private val TAG = "AddPropertyScreenViewModel"


    val forms = listOf(
            AddFormPages.AddressForm,
            AddFormPages.AmenitiesForm,
            AddFormPages.PictureDescForm,
            AddFormPages.ReviewForm)


    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    var listOfProvince = canadianProvinceParser.getData(application)
    var addressFormState: MutableState<FormStates.Address> =
        mutableStateOf(FormStates.Address())
    var posterContact: MutableState<FormStates.PosterContact> =
        mutableStateOf(FormStates.PosterContact())
    var price = mutableStateOf(0)
    var description: MutableState<FormStates.PropertyDescription> =
        mutableStateOf(FormStates.PropertyDescription())


    fun onEvent(event: AddPropertyScreenEvents) {

        when (event) {

            is AddPropertyScreenEvents.OnBackPressed -> {
                sendUiEvent(UiEvent.PopBackStack)
            }

            is AddPropertyScreenEvents.OnClickSubmitForm -> {
                // todo: Send api call to server and then once successful store offline with received URI
                viewModelScope.launch {
//                    val address = addressFormState.value
//                    val contact = posterContact.value
//                    val res = propertyRepository.insertPropertyDetails(
//                        PropertyDetails(
//                            uri = "newProp",
//                            price = price.value,
//                            currency = "$",
//                            favourite = false,
//                            recentlyViewed = false,
//                            imageResourceId = null,
//                            address = Address(
//                                country = "CANADA",
//                                state = address.state.value,
//                                city = address.city.value,
//                                street = address.street.value,
//                                unitNum = address.unitNum.value,
//                                postalCode = address.postalCode.value
//                            ),
//                            posterContact = PosterContact(
//                                name = contact.name.value,
//                                title = contact.title.value,
//                                imageResourceId = contact.imageResourceId.value,
//                                phoneNumber = contact.phoneNumber.value
//                            )
//                        )
//                    )
                    val res = 1
                    val message = let {
                        if (res > 0)
                            "Property created :)"
                        else
                            "Failed to add x( "

                    }
                    sendUiEvent(UiEvent.ShowSnackbar(message))
                    sendUiEvent(UiEvent.Navigate(Routes.HOME_PAGE))
                }
            }

            is AddPropertyScreenEvents.OnClickSubmitPage -> {
                when (event.currentPageRoute) {

                    AddFormPages.AddressForm.route -> {
                        // todo: Validate form
                        // todo: Create and prepare the address object
                        Log.i(TAG, "onEvent: ${event.currentPageRoute}")
                        sendUiEvent(UiEvent.Navigate(AddFormPages.AmenitiesForm.route))
                    }
                    AddFormPages.AmenitiesForm.route -> {
                        sendUiEvent(UiEvent.Navigate(AddFormPages.PictureDescForm.route))
                    }
                    AddFormPages.PictureDescForm.route -> {
                        sendUiEvent(UiEvent.Navigate(AddFormPages.ReviewForm.route))
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