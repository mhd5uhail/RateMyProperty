package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.PropertyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

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

    override fun getProperties(): Flow<Property> {
        return PropertyPreviewParameterProvider().values.asFlow()
    }
}