package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
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
import com.mhdsuhail.ratemyproperty.data.UnitType
import kotlin.reflect.typeOf

@HiltViewModel
class AddPropertyScreenViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    canadianProvinceParser: JsonParser<CanadianProvince>,
    featureDataParser: JsonParser<FeatureData>,
    unitDataParser: JsonParser<UnitType>,
    application: Application
) :
    AndroidViewModel(application) {

    companion object {
        private const val TAG = "AddPropertyScreenViewModel"
        private const val GEO_DATA_FILE = "geodata/canada_states_cities.json"
        private const val FEATURE_DATA_FILE = "features/property_features.json"
        private const val UNIT_TYPES_FILE = "features/unit_types.json"

    }


    val forms = listOf(
        AddFormPages.AddressForm,
        AddFormPages.AmenitiesForm,
        AddFormPages.PictureDescForm,
        AddFormPages.ReviewForm
    )


    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()

    private val listOfProvinceAndCity : List<CanadianProvince> = canadianProvinceParser.getDataAsList(
        application,
        GEO_DATA_FILE
    )


    private val listOfFeatureUnitData : List<FeatureData> = featureDataParser.getDataAsList(
        application,
        FEATURE_DATA_FILE
    )
    private val listOfUnits : List<UnitType> = unitDataParser.getDataAsList(
        application,
        UNIT_TYPES_FILE
    )

    val province2City = HashMap<String, List<String>>()
    val unitsOfFeature = HashMap<String, List<String>>()

    init {
        Log.i(TAG, "${ listOfProvinceAndCity[0] is CanadianProvince }: ")
        Log.i(TAG, "${ listOfProvinceAndCity[0].name}: ")
        listOfProvinceAndCity.forEach { province ->
            province2City[province.name!!] = province.cities + province.towns
        }

        val unitMap = HashMap<Int, UnitType>()
        listOfUnits.forEach { unit ->
            unitMap[unit.id] = unit
        }

        listOfFeatureUnitData.forEach { featureUnitData ->
            unitsOfFeature[featureUnitData.name] = let {
                val units = ArrayList<String>()
                featureUnitData.unitTypes.forEach { unit ->
                    unitMap[unit]?.let { it -> units.add(it.unit) }
                }
                units
            }
        }
    }

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

        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}