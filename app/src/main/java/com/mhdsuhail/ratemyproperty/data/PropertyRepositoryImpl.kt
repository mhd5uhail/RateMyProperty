package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.PropertyDao
import kotlinx.coroutines.flow.Flow

class PropertyRepositoryImpl(private val dao: PropertyDao) : PropertyRepository {
    override suspend fun insertProperty(propertyDetails: PropertyDetails) {
        dao.insertProperty(propertyDetails)
    }

    override suspend fun deleteProperty(propertyDetails: PropertyDetails) {
        dao.deleteProperty(propertyDetails)
    }

    override suspend fun getPropertyById(uri: String): PropertyDetails? {
        return dao.getPropertyById(uri)
    }

    override fun getProperties(): Flow<List<PropertyDetails>> {
        return dao.getAllProperties()
    }

    override fun getFavouriteProperties(): Flow<List<PropertyDetails>> {
        return dao.getAllFavouriteProperties()
    }

    override suspend fun searchProperties(query: String): List<PropertyDetails> {
        // API Call to RMH Server search api
        TODO("Not yet implemented")
    }

    override suspend fun updateProperty(propertyDetails: PropertyDetails) {
        dao.updateProperty(propertyDetails)
    }
}