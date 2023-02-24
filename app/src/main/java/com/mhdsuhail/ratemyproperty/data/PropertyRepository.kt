package com.mhdsuhail.ratemyproperty.data

import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    suspend fun insertProperty(property: Property)

    suspend fun deleteProperty(property: Property)

    suspend fun getPropertyById(uri: String): Property?

    suspend fun updateProperty(property: Property)

    fun getFavouritePropertiesDetails(): Flow<List<PropertyDetails>>

    suspend fun searchPropertiesDetails(query: String): List<PropertyDetails>

    suspend fun getPropertyDetails(uri: String) : PropertyDetails?

    fun getAllPropertiesDetails() : Flow<List<PropertyDetails>>

    suspend fun updatePropertyDetails(propertyDetails: PropertyDetails)

}