package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.Property
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {

    // Insert a property
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProperty(property: Property);

    // Delete a property
    @Delete
    suspend fun deleteProperty(property: Property);

    @Query("SELECT * FROM property WHERE uri = :uri")
    suspend fun getPropertyById(uri: String) : Property?

    @Query("SELECT * FROM property WHERE recent = true")
    fun getAllRecentlyViewedProperties(): Flow<List<Property>>

    @Query("SELECT * FROM property WHERE favourite = true")
    fun getAllFavouriteProperties(): Flow<List<Property>>

    @Query("SELECT * FROM property")
    fun getAllProperties() : Flow<List<Property>>

    @Update
    fun updateProperty(property: Property)

}