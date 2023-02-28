package com.mhdsuhail.ratemyproperty.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mhdsuhail.ratemyproperty.data.*

@Database(
    entities = [PropertyDetails::class, Feature::class, PropertyDescription::class, SearchQuery::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(DateTimeTypeConverters::class)
abstract class RMPDatabase : RoomDatabase() {

    abstract val propertyDetailsDao: PropertyDetailsDao
    abstract val featureDao: FeatureDao
    abstract val searchQueryDao: SearchQueryDao
    abstract val descriptionsDao: DescriptionsDao

    companion object {
        @Volatile
        private var INSTANCE: RMPDatabase? = null

        fun getInstance(context: Context): RMPDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    RMPDatabase::class.java,
                    "rmp_db"
                ).addTypeConverter(DateTimeTypeConverters()).fallbackToDestructiveMigration()
                    .build().also {
                    INSTANCE = it
                }
            }
        }
    }
}