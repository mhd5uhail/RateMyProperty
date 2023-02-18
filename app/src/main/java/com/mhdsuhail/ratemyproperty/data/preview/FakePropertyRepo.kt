package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePropertyRepo : PropertyRepository {
    override suspend fun insertProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun getPropertyById(uri: String): Property? {
        TODO("Not yet implemented")
    }

    override fun getProperties(): Flow<List<Property>> {
        return flow {
            while(true) {
                emit(PropertyPreviewParameterProvider().values.toList())
                delay(10000)
            }
        }
    }

    override suspend fun searchProperties(query: String): List<Property> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProperty(property: Property) {
        TODO("Not yet implemented")
    }
}