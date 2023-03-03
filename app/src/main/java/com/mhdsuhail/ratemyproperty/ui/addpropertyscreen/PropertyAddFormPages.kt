package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

sealed class PropertyAddFormPages {
    object BasicDetails : PropertyAddFormPages() // address, landlord, rent , your role ? etc
    object AmenitiesPage : PropertyAddFormPages() // amenities
    object AddPictures : PropertyAddFormPages()
    object ReviewPage : PropertyAddFormPages()
}
