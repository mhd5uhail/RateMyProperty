package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

sealed class AddPropertyScreenEvents{
    data class OnClickSubmitPage(private val page : AddFormPages) : AddPropertyScreenEvents()
    data class  OnClickBack(private val lastPage : AddFormPages) : AddPropertyScreenEvents()
    object  OnClickSubmitForm : AddPropertyScreenEvents()
    object OnBackPressed: AddPropertyScreenEvents()
}