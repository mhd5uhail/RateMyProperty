package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import com.mhdsuhail.ratemyproperty.data.Feature

@Entity
interface  FeatureDao {
    @Insert
    suspend fun insertFeature(feature: Feature)

    @Delete
    suspend fun deleteFeature(feature: Feature)

    @Query("SELECT * FROM features id = :id")
    suspend fun getFeaturesById(id: Int): Feature?

    @Query("SELECT * FROM features propId = :id")
    suspend fun getFeatureByPropId(uri: String): List<Feature>

}