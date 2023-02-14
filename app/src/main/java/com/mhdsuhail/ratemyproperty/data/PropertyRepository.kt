package com.mhdsuhail.ratemyproperty.data

import kotlinx.coroutines.flow.Flow

interface PropertyRepository {
    suspend fun insertProperty(property: Property)

    suspend fun deleteProperty(property: Property)

    suspend fun getPropertyById(id: Int): Property?

    fun getProperties(): Flow<List<Property>>
}