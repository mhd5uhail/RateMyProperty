package com.mhdsuhail.ratemyproperty.data

data class Property(
    val propertyId : String,
    val address: Address,
    val price: Int,
    val currency: String,
    val imageUrl: String?,
    val features: List<Feature>,
    val posterContact: PosterContact
)