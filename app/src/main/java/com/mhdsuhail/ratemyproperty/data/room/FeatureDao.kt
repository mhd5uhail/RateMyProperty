package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.Feature

@Dao
interface  FeatureDao : BaseDao<Feature>{

    @Query("SELECT * FROM features WHERE id = :id")
    suspend fun getFeaturesById(id: Int): Feature?

    @Query("SELECT * FROM features WHERE prop_uri = :prop_uri")
    suspend fun getFeatureByPropId(prop_uri: String): List<Feature>

}