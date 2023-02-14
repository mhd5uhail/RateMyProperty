package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.FeatureDao
import com.mhdsuhail.ratemyproperty.data.room.PropertyDao

class PropertyPageStore(private val propertyDao: PropertyDao,
                    private val featureDao: FeatureDao) {

    suspend fun getPropertyPage(uri: String) : PropertyPage? {
        val features = featureDao.getFeatureByPropId(uri);
        val property = propertyDao.getPropertyById(uri)
        return property?.let { it->
            PropertyPage(it,features)
        }
    }

}