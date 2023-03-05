package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

sealed class AddFormPages(val route: String, val step:Int,val title: String) {
    object AddressForm : AddFormPages("address_form",0, "Address")
    object AmenitiesForm : AddFormPages("amenities_form",1, "Amenities")
    object PictureDescForm : AddFormPages("picture_form",2,"Picture")
    object ReviewForm : AddFormPages("review_form",3,"Review")
}