package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.FeatureDao

class FeatureRepositoryImpl(private val dao: FeatureDao) : FeatureRepository{
    override suspend fun insertFeature(feature: Feature) {
        dao.insert(feature)
    }

    override suspend fun deleteFeature(feature: Feature) {
        dao.delete(feature)
    }

    override suspend fun getFeatureById(id: Int): Feature? {
        return dao.getFeaturesById(id)
    }


    override suspend fun getFeatureByPropId(uri: String): List<Feature> {
        return dao.getFeatureByPropId(uri)
    }
}