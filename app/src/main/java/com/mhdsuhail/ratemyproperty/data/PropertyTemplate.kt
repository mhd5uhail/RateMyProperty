package com.mhdsuhail.ratemyproperty.data

import androidx.room.Embedded
import androidx.room.Relation

abstract class PropertyTemplate{
    abstract val propertyDetails: PropertyDetails
    abstract val features: List<Feature>
}