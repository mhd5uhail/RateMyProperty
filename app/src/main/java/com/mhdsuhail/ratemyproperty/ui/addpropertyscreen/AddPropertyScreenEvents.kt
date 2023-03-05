package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

sealed class AddPropertyScreenEvents{
    data class OnClickSubmitPage(val page : AddFormPages) : AddPropertyScreenEvents()
    data class  OnClickBack(val lastPage : AddFormPages) : AddPropertyScreenEvents()
    object  OnClickSubmitForm : AddPropertyScreenEvents()
    object OnBackPressed: AddPropertyScreenEvents()
}