package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.Property

@Dao
interface PropertyDao {

    @Transaction
    @Query("SELECT * FROM property_details WHERE uri = :uri")
    suspend fun getPropertyById(uri: String) : Property?

    @Transaction
    @Insert
    suspend fun insertProperty(property: Property)

    @Transaction
    @Delete
    suspend fun deleteProperty(property: Property)

    @Transaction
    @Update
    suspend fun updateProperty(property: Property)

}