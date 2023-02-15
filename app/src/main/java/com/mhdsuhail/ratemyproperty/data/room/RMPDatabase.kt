package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mhdsuhail.ratemyproperty.data.Address
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.PosterContact
import com.mhdsuhail.ratemyproperty.data.Property

@Database(
    entities = [Property::class,Feature::class],
    version = 1,
    exportSchema = false
)
abstract class RMPDatabase : RoomDatabase() {
    abstract val propertyDao: PropertyDao
    abstract val featureDao: FeatureDao
    abstract val propertyWithExtraInfoDao : PropertyWithExtraInfoDao
}