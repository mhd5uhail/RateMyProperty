package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import kotlinx.coroutines.flow.Flow
@Dao
interface PropertyDetailsDao : BaseDao<PropertyDetails> {

    // Read functions for PropertyDetails
    @Query("SELECT * FROM property_details WHERE uri = :uri")
    fun getPropertyDetails(uri: String) : PropertyDetails?

    @Query("SELECT * FROM property_details")
    fun getAllPropertiesDetails() : Flow<List<PropertyDetails>>

    @Query("SELECT * FROM property_details WHERE recent = true")
    fun getAllRecentlyViewedProperties(): Flow<List<PropertyDetails>>

    @Query("SELECT * FROM property_details WHERE favourite = true")
    fun getAllFavouriteProperties(): Flow<List<PropertyDetails>>

}