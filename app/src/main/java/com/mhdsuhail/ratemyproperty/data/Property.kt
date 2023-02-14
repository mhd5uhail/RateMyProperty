package com.mhdsuhail.ratemyproperty.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "property")
data class Property(
    @PrimaryKey @ColumnInfo(name = "uri") val uri: String,  // UUID corresponding with the backend database
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "recent") val recentlyViewed: Boolean,
    @ColumnInfo(name = "favourite")  val favourite: Boolean,
    @ColumnInfo(name = "imageId")  val imageResourceId: Int?,
    @Embedded
    val address: Address,
)