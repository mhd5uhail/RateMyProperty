package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePropertyRepo : PropertyRepository {
    override suspend fun insertProperty(propertyDetails: PropertyDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProperty(propertyDetails: PropertyDetails) {
        TODO("Not yet implemented")
    }

    override suspend fun getPropertyById(uri: String): PropertyDetails? {
        TODO("Not yet implemented")
    }

    override fun getProperties(): Flow<List<PropertyDetails>> {
        return flow {
            while(true) {
                emit(PropertyPreviewParameterProvider().values.toList())
                delay(10000)
            }
        }
    }

    override fun getFavouriteProperties(): Flow<List<PropertyDetails>> {
        return flow {
            while(true) {
                emit(PropertyPreviewParameterProvider().values.toList())
                delay(10000)
            }
        }
    }

    override suspend fun searchProperties(query: String): List<PropertyDetails> {
        return PropertyPreviewParameterProvider().values.toList()
    }

    override suspend fun updateProperty(propertyDetails: PropertyDetails) {
        //TODO("Not yet implemented")
    }

}