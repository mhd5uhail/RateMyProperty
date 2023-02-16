package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import com.mhdsuhail.ratemyproperty.data.Address
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.PosterContact

// State holder class for the property screen state

data class PropertyScreenState(
    var isfav: Boolean = false,
    var uri: String = "",
    var isRecent: Boolean = false,
    var currency: String = "",
    var imageResource: Int? = null,
    var price: Int = 0,
    var address: Address = Address("", "", "", "", "", ""),
    var features : List<Feature>  = listOf(Feature(1,"","",null,"","","")),
    var posterContact: PosterContact = PosterContact("", "", null, ""),
    var description: String? = ""
)