package com.mhdsuhail.ratemyproperty.data

import android.net.Uri
import androidx.room.*

@Entity(tableName = "property_details")
@TypeConverters(AddressTypeConverter::class)
data class PropertyDetails(
    @PrimaryKey @ColumnInfo(index = true, name = "uri") val uri: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "recent") val recentlyViewed: Boolean,
    @ColumnInfo(name = "favourite")  val favourite: Boolean,
    @ColumnInfo(name = "imagePropertyUri")  val imagePropertyUri: Int?,
    @ColumnInfo(name = "address") val address: Address,
    @Embedded
    val contributor: Contributor
)