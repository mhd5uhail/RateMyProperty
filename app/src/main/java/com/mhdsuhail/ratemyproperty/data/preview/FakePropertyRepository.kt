package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakePropertyRepository() : PropertyRepository {

    override suspend fun insertProperty(property: Property) {
        TODO("Not yet implemented")

    }

    override suspend fun deleteProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun getPropertyById(uri: String): Property? {
        return Property(
            propertyDetails = PropertyDetails(
                uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",
                price = 1300,
                currency = "$",
                recentlyViewed = true,
                favourite = true,
                imageResourceId = null,
                address = Address("Canada", "ON", "Toronto", "88 Harbor St N", "1432", "H2A 4L2"),
                posterContact = PosterContact(
                    "Mohammed Suhail",
                    "Realtor",
                    R.drawable.sample_realtor,
                    "523-349-233"
                ),
            ),
            features = FeaturePreviewProvider().values.toList(),
            description = PropertyDescription(prop_uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis finibus sem. Duis nec dolor et tortor malesuada pellentesque. Suspendisse porttitor tempus lectus, non commodo orci rhoncus et. Praesent odio est, ultricies sed augue ut, laoreet congue magna. Duis semper suscipit bibendum. Maecenas semper dolor vel nulla congue dignissim. Ut pretium lobortis felis a tristique\n")
        )
    }

    override suspend fun updateProperty(property: Property) {
        TODO("Not yet implemented")
    }

    override suspend fun getPropertyDetailsById(uri: String): PropertyDetails? {
        TODO("Not yet implemented")
    }

    override fun getAllFavouritePropertyDetails(): Flow<List<PropertyDetails>> {
        return flow {
            while(true) {
                emit(PropertyPreviewParameterProvider().values.toList())
                delay(10000)
            }
        }
    }

    override suspend fun searchPropertiesDetails(query: String): List<PropertyDetails> {
        return PropertyPreviewParameterProvider().values.toList()
    }

    override fun getAllPropertiesDetails(): Flow<List<PropertyDetails>> {
        return flow {
            while(true) {
                emit(PropertyPreviewParameterProvider().values.toList())
                delay(10000)
            }
        }
    }

    override suspend fun updatePropertyDetails(propertyDetails: PropertyDetails) {
        TODO("Not yet implemented")
    }
}