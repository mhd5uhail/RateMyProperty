package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.AddressDao
import com.mhdsuhail.ratemyproperty.data.room.FeatureDao
import com.mhdsuhail.ratemyproperty.data.room.PosterContactDao
import com.mhdsuhail.ratemyproperty.data.room.PropertyDao
import kotlinx.coroutines.flow.Flow

class PropertyStore(private val propertyDao: PropertyDao,
                    private val addressDao: AddressDao,
                    private val featureDao: FeatureDao,
                    private val contactDao: PosterContactDao) {

    fun getAllRecentPropertyCards(): Flow<List<PropertyCard>>{
    }

    fun getAllFavouritePropertyCards() : Flow<List<PropertyCard>>{

    }

    suspend fun getPropertyCardById(id: Int) : PropertyCard?{

    }

    suspend fun getPropertyFull(id: Int) : PropertyFull?{

    }

}