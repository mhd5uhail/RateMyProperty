package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.PropertyDao
import kotlinx.coroutines.flow.Flow

class PropertyRepositoryImpl(private val dao: PropertyDao) : PropertyRepository {
    override suspend fun insertProperty(property: Property) {
        dao.insertProperty(property)
    }

    override suspend fun deleteProperty(property: Property) {
        dao.deleteProperty(property)
    }

    override suspend fun getPropertyById(uri: String): Property? {
        return dao.getPropertyById(uri)
    }

    override fun getProperties(): Flow<List<Property>> {
        return dao.getAllProperties()
    }

    override fun getFavouriteProperties(): Flow<List<Property>> {
        return dao.getAllFavouriteProperties()
    }

    override suspend fun searchProperties(query: String): List<Property> {
        // API Call to RMH Server search api
        TODO("Not yet implemented")
    }

    override suspend fun updateProperty(property: Property) {
        dao.updateProperty(property)
    }
}