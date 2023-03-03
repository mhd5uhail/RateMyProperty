package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

sealed class AddPropertyScreenEvents{
    data class OnClickSubmitPage(private val page : PropertyAddFormPages) : AddPropertyScreenEvents()
    data class  OnClickBack(private val lastPage : PropertyAddFormPages) : AddPropertyScreenEvents()
    object  OnClickSubmitForm : AddPropertyScreenEvents()
}