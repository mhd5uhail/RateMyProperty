package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import com.mhdsuhail.ratemyproperty.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPropertyScreenViewModel @Inject constructor (private val propertyRepository: PropertyRepository) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()

    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: AddPropertyScreenEvents){

        when(event){

            is AddPropertyScreenEvents.OnClickBack -> {

            }

            is AddPropertyScreenEvents.OnClickSubmitForm -> {

            }

            is AddPropertyScreenEvents.OnClickSubmitPage -> {

            }

        }

    }

    private fun sendUiEvent(event: UiEvent){
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}