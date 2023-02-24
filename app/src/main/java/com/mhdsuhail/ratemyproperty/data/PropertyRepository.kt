package com.mhdsuhail.ratemyproperty.data

import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    // CRUD for main PropertyObject
    suspend fun insertProperty(propertyDetails: Property)

    suspend fun deleteProperty(propertyDetails: Property)

    suspend fun getPropertyById(uri: String): Property?

    suspend fun updateProperty(property: Property)

    // Read functions for PropertyDetails

    fun getFavouritePropertiesDetails(): Flow<List<PropertyDetails>>

    suspend fun searchPropertiesDetails(query: String): List<PropertyDetails>

    suspend fun getPropertyDetails(uri: String) : PropertyDetails?

    suspend fun getAllPropertiesDetails(uri: String) : Flow<List<Property>>
}