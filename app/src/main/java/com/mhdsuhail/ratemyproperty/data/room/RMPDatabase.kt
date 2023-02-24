package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mhdsuhail.ratemyproperty.data.*

@Database(
    entities = [PropertyDetails::class,Feature::class,SearchQuery::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(DateTimeTypeConverters::class)
abstract class RMPDatabase : RoomDatabase() {
    abstract val propertyDao: PropertyDao
    abstract val featureDao: FeatureDao
    abstract val searchQueryDao : SearchQueryDao
}