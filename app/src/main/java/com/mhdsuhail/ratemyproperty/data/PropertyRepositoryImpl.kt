package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.PropertyDao
import com.mhdsuhail.ratemyproperty.data.room.PropertyDetailsDao
import kotlinx.coroutines.flow.Flow

class PropertyRepositoryImpl(
    private val propertyDetailsDao: PropertyDetailsDao,
    private val propertyDao: PropertyDao
) : PropertyRepository {

    override suspend fun insertPropertyDetails(propertyDetails: PropertyDetails) : Long {
        return propertyDetailsDao.insert(propertyDetails)
    }


    override suspend fun getPropertyById(uri: String): Property? {
        return propertyDao.getPropertyById(uri)
    }

    override suspend fun deletePropertyDetails(propertyDetails: PropertyDetails) : Int {
       return propertyDetailsDao.delete(propertyDetails)
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