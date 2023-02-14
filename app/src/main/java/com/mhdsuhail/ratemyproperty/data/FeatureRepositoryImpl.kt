package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.FeatureDao

class FeatureRepositoryImpl(private val dao: FeatureDao) : FeatureRepository{
    override suspend fun insertFeature(feature: Feature) {
        dao.insertFeature(feature)
    }

    override suspend fun deleteFeature(feature: Feature) {
        dao.deleteFeature(feature)
    }

    override suspend fun getFeatureById(id: Int): Feature? {
        return dao.getFeatureById(id)
    }

    override suspend fun getFeatureByPropId(id: Int): Feature? {
        return dao.getFeatureByPropId(id)
    }
}