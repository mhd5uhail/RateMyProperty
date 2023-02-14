package com.mhdsuhail.ratemyproperty.data

interface FeatureRepository {

    suspend fun insertFeature(feature: Feature)

    suspend fun deleteFeature(feature: Feature)

    suspend fun getFeatureById(id: Int): Feature?

    suspend fun getFeatureByPropId(uri: String): List<Feature>


}