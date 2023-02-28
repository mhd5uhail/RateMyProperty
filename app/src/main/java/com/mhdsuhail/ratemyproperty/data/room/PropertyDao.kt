package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mhdsuhail.ratemyproperty.data.Property

@Dao
interface PropertyDao {
    @Transaction
    @Query("SELECT * FROM property_details WHERE uri = :uri")
    suspend fun getPropertyById(uri: String) : Property?
}