package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.lifecycle.ViewModel
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(private val propertyRepository: PropertyRepository) :
    ViewModel() {
    val properties = propertyRepository.getProperties()

}