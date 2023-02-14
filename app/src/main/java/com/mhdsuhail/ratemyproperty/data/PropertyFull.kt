package com.mhdsuhail.ratemyproperty.data

data class PropertyFull(
    val property: Property,
    val address: Address,
    val feature: Feature,
    val contact: PosterContact
)