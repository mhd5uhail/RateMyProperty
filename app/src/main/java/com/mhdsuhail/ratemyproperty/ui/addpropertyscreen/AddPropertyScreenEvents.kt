package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

sealed class AddPropertyScreenEvents{
    data class OnClickSubmitPage(val currentPageRoute : String) : AddPropertyScreenEvents()
    object  OnClickSubmitForm : AddPropertyScreenEvents()
    object OnBackPressed: AddPropertyScreenEvents()
}