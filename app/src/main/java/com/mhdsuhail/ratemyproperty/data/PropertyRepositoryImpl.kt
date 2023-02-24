package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.PropertyDao
import com.mhdsuhail.ratemyproperty.data.room.PropertyDetailsDao
import kotlinx.coroutines.flow.Flow

class PropertyRepositoryImpl(
    private val propertyDao: PropertyDao,
    private val propertyDetailsDao: PropertyDetailsDao
) : PropertyRepository {
    override suspend fun insertProperty(property: Property) {
        propertyDao.insertProperty(property)
    }

    override suspend fun deleteProperty(property: Property) {
        propertyDao.deleteProperty(property)
    }

    override suspend fun getPropertyById(uri: String): Property? {
        return propertyDao.getPropertyById(uri)
    }

    override suspend fun updateProperty(property: Property) {
        propertyDao.updateProperty(property)
    }

    override suspend fun getPropertyDetailsById(uri: String): PropertyDetails? {
        return propertyDetailsDao.getPropertyDetails(uri)
    }

    override fun getAllFavouritePropertyDetails(): Flow<List<PropertyDetails>> {
        return propertyDetailsDao.getAllPropertiesDetails()
    }

    override suspend fun searchPropertiesDetails(query: String): List<PropertyDetails> {
        TODO("Not yet implemented")
    }

    override fun getAllPropertiesDetails(): Flow<List<PropertyDetails>> {
        return propertyDetailsDao.getAllPropertiesDetails()
    }

    override suspend fun updatePropertyDetails(propertyDetails: PropertyDetails) {
        propertyDetailsDao.update(propertyDetails)
    }
}