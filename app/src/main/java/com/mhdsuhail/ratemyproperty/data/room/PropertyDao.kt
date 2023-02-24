package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {


    // CRUD for  Property

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(property: Property);

    @Delete
    suspend fun deleteProperty(property: Property);

    @Query("SELECT * FROM property_details WHERE uri = :uri")
    suspend fun getPropertyById(uri: String) : Property?

    @Update
    fun updateProperty(property: Property)

    // Read functions for PropertyDetails

    @Query("SELECT * FROM property_details WHERE uri = :uri")
    fun getPropertyDetails(uri: String) : PropertyDetails?

    @Query("SELECT * FROM property_details")
    fun getAllPropertiesDetails() : Flow<List<PropertyDetails>>

    @Query("SELECT * FROM property_details WHERE recent = true")
    fun getAllRecentlyViewedProperties(): Flow<List<PropertyDetails>>

    @Query("SELECT * FROM property_details WHERE favourite = true")
    fun getAllFavouriteProperties(): Flow<List<PropertyDetails>>

    @Update
    fun updatePropertyDetails(propertyDetails: PropertyDetails)

}