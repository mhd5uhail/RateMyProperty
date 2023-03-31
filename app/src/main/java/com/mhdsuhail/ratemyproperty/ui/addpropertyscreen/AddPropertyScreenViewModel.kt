package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.*
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
    private val assetRepository: AssetRepository,
) :
    ViewModel() {

    companion object {
        private const val TAG = "AddPropertyScreenViewModel"
    }

    val forms = listOf(
        AddFormPages.AddressForm,
        AddFormPages.AmenitiesForm,
        AddFormPages.PictureDescForm,
        AddFormPages.ReviewForm
    )


    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    val province2City = assetRepository.getProvince2CityMap()
    val feature2Unit = assetRepository.getFeature2UnitMap()


    val selectedImageUri = mutableStateOf<Uri?>(null)


    // TODO: Move data organization logic to asset repository

    var addressFormState = FormStates.Address()
    var featuresListState = mutableStateListOf<Feature>()
    var posterContact = FormStates.PosterContact()
    var price = mutableStateOf("")
    var descriptionFormState = FormStates.PropertyDescription()

    fun onEvent(event: AddPropertyScreenEvents) {

        when (event) {

            is AddPropertyScreenEvents.ClickRemoveImage -> {
                selectedImageUri.value = null
            }

            is AddPropertyScreenEvents.ClickAddNewImage -> {
                selectedImageUri.value = event.uri
            }
            is AddPropertyScreenEvents.FeatureDismissed -> {
                featuresListState.remove(event.feature)
            }

            is AddPropertyScreenEvents.OnClickSubmitFeatureCreateDialog -> {
                if (featuresListState.any { it.name == event.feature.name }) {
                    sendUiEvent(UiEvent.ShowSnackbar("Cannot create duplicate feature !"))
                } else {
                    featuresListState.add(event.feature)
                }
            }

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

            is AddPropertyScreenEvents.OnClickNextPage -> {
                Log.i(TAG, "onEvent: ${event.currentPageRoute}")
                when (event.currentPageRoute) {
                    AddFormPages.AddressForm.route -> {
                        if (FieldValidators.Address.validateAddress(
                                unitNumber = addressFormState.unitNum.value,
                                streetName = addressFormState.street.value,
                                province = addressFormState.province.value,
                                city = addressFormState.city.value,
                                postalCode = addressFormState.postalCode.value,
                                province2CityMap = province2City
                            )
                        ) {
                            sendUiEvent(UiEvent.Navigate(AddFormPages.AmenitiesForm.route))
                        } else {
                            sendUiEvent(UiEvent.ShowSnackbar("Some values are invalid!"))
                        }
                    }
                    AddFormPages.AmenitiesForm.route -> {
                        if (featuresListState.isEmpty()) {
                            sendUiEvent(UiEvent.ShowSnackbar("Please any amenities available"))
                        } else {
                            sendUiEvent(UiEvent.Navigate(AddFormPages.PictureDescForm.route))
                        }
                    }
                    AddFormPages.PictureDescForm.route -> {
                        if (descriptionFormState.text.value.isNotBlank() &&
                            selectedImageUri.value != null &&
                            price.value != null
                        ) {
                            sendUiEvent(UiEvent.Navigate(AddFormPages.ReviewForm.route))
                        } else {
                            sendUiEvent(UiEvent.ShowSnackbar("Please provide more details"))
                        }
                    }
                    AddFormPages.ReviewForm.route -> {
                        // On submitting the final review page form is completed
                        onEvent(AddPropertyScreenEvents.OnClickSubmitForm)
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