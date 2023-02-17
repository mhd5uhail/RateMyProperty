package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val propertyRepository: PropertyRepository) :
    ViewModel() {

    val properties = propertyRepository.getProperties()

    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    init {



    }

    fun onEvent(event: SearchScreenEvents){

        when(event){

            is SearchScreenEvents.OnAddToFavouritesClick -> {

            }

            is SearchScreenEvents.OnPropertyCardClick -> {

            }
            is SearchScreenEvents.OnShowResultsClick -> {

            }

        }

    }




}