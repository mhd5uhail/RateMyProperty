package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    // Insert a property
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(property: Property);

    // Delete a property
    @Delete
    suspend fun deleteProperty(property: Property);

    @Query("SELECT * FROM property_details WHERE uri = :uri")
    suspend fun getPropertyById(uri: String) : Property?

    @Query("SELECT * FROM property_details WHERE recent = true")
    fun getAllRecentlyViewedProperties(): Flow<List<Property>>

    @Query("SELECT * FROM property_details WHERE favourite = true")
    fun getAllFavouriteProperties(): Flow<List<Property>>

    @Query("SELECT * FROM property_details")
    fun getAllProperties() : Flow<List<Property>>

    @Update
    fun updateProperty(property: Property)

    @Query("SELECT * FROM property_details WHERE uri = :uri")
    fun getPropertyDetails(uri: String) : PropertyDetails?

    @Query("SELECT * FROM property_details")
    fun getAllPropertiesDetails(uri: String) : Flow<List<Property>>

}