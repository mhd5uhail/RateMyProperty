package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val propertyRepository: PropertyRepository,
    private val searchHistoryRepository: SearchHistoryRepository
) :
    ViewModel() {

    val searchResults = mutableListOf<Property>()
    val recentlyViewed = propertyRepository.getProperties()
    val queryString = mutableStateOf("")
    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    fun onEvent(event: SearchScreenEvents) {
        when (event) {

            is SearchScreenEvents.OnAddToFavouritesClick -> {
                /*TODO Implementation not completed*/
                viewModelScope.launch {
                    propertyRepository.updateProperty(property = event.property.copy(favourite = true))
                    sendUIEvent(UiEvent.ShowSnackbar("Added to Favorites!"))
                }
            }
            is SearchScreenEvents.OnPropertyCardClick -> {
                sendUIEvent(UiEvent.Navigate(Routes.PROP_VIEW_PAGE + "?prop_uri=${event.property.uri}"))
                viewModelScope.launch {
                    searchHistoryRepository.insertSearchQuery(
                        SearchQuery(
                            query = queryString.value,
                            date = LocalDateTime.now(),
                            resultCount = searchResults.size,
                        )
                    )
                }
            }

            is SearchScreenEvents.OnSearchBarClick ->{
                sendUIEvent(UiEvent.Navigate(Routes.SEARCH_LIST))
            }

            is SearchScreenEvents.OnSearchQueryChange -> {
                queryString.value = event.query
                viewModelScope.launch {
                    val results = propertyRepository.searchProperties(event.query)
                        .filter { property ->
                            AddressTypeConverter().toString(property.address)
                                .contains(event.query, ignoreCase = true)
                        }
                    searchResults.clear()
                    searchResults.addAll(results)
                }
            }

        }

    }

    private fun sendUIEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvents.send(event)
        }
    }


}