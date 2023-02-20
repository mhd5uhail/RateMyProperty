package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val propertyRepository: PropertyRepository) :
    ViewModel() {

    val searchResults = mutableListOf<Property>()
    val propertyList = propertyRepository.getProperties()
    val queryString = mutableStateOf("")
    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()




    fun onEvent(event: SearchScreenEvents){
        when(event){

            is SearchScreenEvents.OnAddToFavouritesClick -> {
                /*TODO Implementation not completed*/
                viewModelScope.launch {
                    propertyRepository.updateProperty(property = event.property.copy(favourite = true))
                    sendUIEvent(UiEvent.ShowSnackbar("Added to Favorites!"))
                }
            }
            is SearchScreenEvents.OnPropertyCardClick -> {
                // Need to couple property_uri with this
                sendUIEvent(UiEvent.Navigate(Routes.PROP_VIEW_PAGE + "?prop_uri=${event.property.uri}"))
            }

            is SearchScreenEvents.OnSearchQueryChange -> {
                queryString.value = event.query
            }

            is SearchScreenEvents.OnShowResultsClick -> {
                /*TODO Implementation not completed*/
//                viewModelScope.launch {
//                    val results = propertyRepository.searchProperties(event.query)
//                    searchResults.addAll(results)
//                }
                sendUIEvent(UiEvent.ShowSnackbar("Searching ..."))
            }

        }

    }

    private fun sendUIEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }




}