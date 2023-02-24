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

    override suspend fun updateProperty(property: Property) {
        dao.updateProperty(property)
    }

    override fun getFavouritePropertiesDetails(): Flow<List<PropertyDetails>> {
        return dao.getAllFavouriteProperties()
    }

    override suspend fun searchPropertiesDetails(query: String): List<PropertyDetails> {
        TODO("Not yet implemented")
    }

    override suspend fun getPropertyDetails(uri: String): PropertyDetails? {
        return dao.getPropertyDetails(uri)
    }

    override fun getAllPropertiesDetails(uri: String): Flow<List<PropertyDetails>> {
        return dao.getAllPropertiesDetails()
    }
}