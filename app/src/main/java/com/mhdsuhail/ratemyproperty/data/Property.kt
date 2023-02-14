package com.mhdsuhail.ratemyproperty.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class Property(
    @PrimaryKey
    val id : Int? = null,
    val serverReference: String,  // UUID corresponding with the backend database
    val price: Int,
    val currency: String,
    val recentlyViewed: Boolean,
    val favourite: Boolean,
    val imageResourceId: Int?
    //val features: List<Feature>,
    //val posterContact: PosterContact
)