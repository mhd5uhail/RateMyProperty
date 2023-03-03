package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.DescriptionsDao

class PropertyDescriptionRepoImpl(private val dao: DescriptionsDao) : PropertyDescriptionRepository{

    override suspend fun insert(propertyDescription: PropertyDescription) {
        dao.insert(propertyDescription)
    }

    override suspend fun delete(propertyDescription: PropertyDescription) {
        dao.delete(propertyDescription)
    }

    override suspend fun update(propertyDescription: PropertyDescription) {
        dao.update(propertyDescription)
    }

    override suspend fun getById(uri: String): PropertyDescription? {
        return dao.getPropertyDescription(uri)
    }
}