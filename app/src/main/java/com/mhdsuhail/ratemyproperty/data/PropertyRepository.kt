package com.mhdsuhail.ratemyproperty.data

import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    suspend fun insertProperty(property: Property)

    suspend fun deleteProperty(property: Property)

    suspend fun getPropertyById(uri: String): Property?

    suspend fun updateProperty(property: Property)

    suspend fun getPropertyDetailsById(uri: String) : PropertyDetails?

    fun getAllFavouritePropertyDetails(): Flow<List<PropertyDetails>>

    suspend fun searchPropertiesDetails(query: String): List<PropertyDetails>

    fun getAllPropertiesDetails() : Flow<List<PropertyDetails>>

    suspend fun updatePropertyDetails(propertyDetails: PropertyDetails)

}