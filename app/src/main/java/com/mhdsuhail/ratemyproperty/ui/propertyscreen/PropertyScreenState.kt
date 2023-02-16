package com.mhdsuhail.ratemyproperty.ui.propertyscreen

import com.mhdsuhail.ratemyproperty.data.Address
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.PosterContact
import java.io.FileDescriptor

// State holder class for the property screen state

data class PropertyScreenState(
    val isfav: Boolean = false,
    val uri: String = "",
    val isRecent: Boolean = false,
    val currency: String = "",
    val imageResource: Int? = null,
    val price: Int = 0,
    val address: Address = Address("", "", "", "", "", ""),
    val features : List<Feature> ,
    val posterContact: PosterContact = PosterContact("", "", null, ""),
    val description: String? = ""
)