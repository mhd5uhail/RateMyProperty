package com.mhdsuhail.ratemyproperty.data

import androidx.room.Embedded
import androidx.room.Relation

data class PropertyWithExtraInfo(
    @Embedded val property: Property,
    @Relation(
        parentColumn = "uri",
        entityColumn = "prop_uri"
    )
    val features: List<Feature>,
    val description: String?
)