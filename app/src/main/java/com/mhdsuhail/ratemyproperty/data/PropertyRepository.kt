package com.mhdsuhail.ratemyproperty.data

import kotlinx.coroutines.flow.Flow

interface PropertyRepository {
    suspend fun insertProperty(property: Property)

    suspend fun deleteProperty(property: Property)

    suspend fun getPropertyById(uri: String): Property?

    fun getProperties(): Flow<Property>
}