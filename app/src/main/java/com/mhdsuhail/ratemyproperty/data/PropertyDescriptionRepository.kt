package com.mhdsuhail.ratemyproperty.data

interface PropertyDescriptionRepository {

    suspend fun insert(propertyDescription: PropertyDescription)

    suspend fun delete(propertyDescription: PropertyDescription)

    suspend fun update(propertyDescription: PropertyDescription)

    suspend fun getById(uri: String): PropertyDescription?

}