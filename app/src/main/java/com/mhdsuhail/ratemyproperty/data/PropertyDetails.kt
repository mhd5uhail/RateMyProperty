package com.mhdsuhail.ratemyproperty.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property_details")
data class PropertyDetails(
    @PrimaryKey @ColumnInfo(name = "uri") val uri: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "recent") val recentlyViewed: Boolean,
    @ColumnInfo(name = "favourite")  val favourite: Boolean,
    @ColumnInfo(name = "imageId")  val imageResourceId: Int?,
    @ColumnInfo(name ="description") val description: String?,
    @Embedded
    val address: Address,
    @Embedded
    val posterContact: PosterContact
)