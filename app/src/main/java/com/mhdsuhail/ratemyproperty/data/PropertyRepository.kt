package com.mhdsuhail.ratemyproperty.data

import kotlinx.coroutines.flow.Flow

interface PropertyRepository {

    suspend fun getPropertyById(uri: String): Property?

    suspend fun insertPropertyDetails(propertyDetails: PropertyDetails) :Long

    suspend fun deletePropertyDetails(propertyDetails: PropertyDetails) : Int

    suspend fun getPropertyDetailsById(uri: String) : PropertyDetails?

    fun getAllFavouritePropertyDetails(): Flow<List<PropertyDetails>>

    suspend fun searchPropertiesDetails(query: String): List<PropertyDetails>

    fun getAllPropertiesDetails() : Flow<List<PropertyDetails>>

    suspend fun updatePropertyDetails(propertyDetails: PropertyDetails)

}