package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class FormStates {
    data class Address(
        var country: MutableState<String> = mutableStateOf(""),
        var province: MutableState<String> = mutableStateOf(""),
        var city: MutableState<String> = mutableStateOf(""),
        var street: MutableState<String> = mutableStateOf(""),
        var unitNum: MutableState<String> = mutableStateOf(""),
        var postalCode: MutableState<String> = mutableStateOf("")
    ) : FormStates()

    data class PosterContact(
        val name: MutableState<String> = mutableStateOf(""),
        val title: MutableState<String> = mutableStateOf(""),
        val imageResourceId: MutableState<Int?> = mutableStateOf(null),
        val phoneNumber: MutableState<String> = mutableStateOf("")
    ): FormStates()

    data class PropertyDescription(
        val text: MutableState<String> = mutableStateOf("")
    ): FormStates()
}