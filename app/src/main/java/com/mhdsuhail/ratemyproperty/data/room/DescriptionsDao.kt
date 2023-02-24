package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.PropertyDescription

@Dao
interface DescriptionsDao : BaseDao<PropertyDescription> {

    @Query("SELECT * FROM descriptions WHERE prop_uri = :uri")
    suspend fun getPropertyDescription(uri : String) : PropertyDescription?

}