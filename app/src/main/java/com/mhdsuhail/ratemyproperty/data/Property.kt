package com.mhdsuhail.ratemyproperty.data

import androidx.room.Embedded
import androidx.room.Relation

data class Property(
    @Embedded val propertyDetails: PropertyDetails,
    @Relation(
        parentColumn = "uri",
        entityColumn = "prop_uri"
    )
    val features: List<Feature>,
    @Relation(
        parentColumn = "uri",
        entityColumn = "prop_uri"
    )
    val description : PropertyDescription)