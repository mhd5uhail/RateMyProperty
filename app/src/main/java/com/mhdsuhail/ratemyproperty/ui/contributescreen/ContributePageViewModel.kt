package com.mhdsuhail.ratemyproperty.ui.contributescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ContributePageViewModel @Inject constructor() :
    ViewModel() {

        private val _uiEvent = Channel<UiEvent>()

        val uiEvent = _uiEvent.receiveAsFlow()


        fun onEvent(events: ContributeScreenEvents) {
            when(events){

                is ContributeScreenEvents.OnClickAddProperty -> {
                    sendUiEvent(UiEvent.Navigate(Routes.ADD_FORM))
                }
                is ContributeScreenEvents.OnClickRequestChange -> {
                    // TODO :Open change request form
                }
            }

        }

        private fun sendUiEvent(event: UiEvent){
            viewModelScope.launch {
                _uiEvent.send(event)
            }
        }
}