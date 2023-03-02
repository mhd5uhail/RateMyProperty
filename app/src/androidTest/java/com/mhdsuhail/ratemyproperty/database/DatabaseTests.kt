package com.mhdsuhail.ratemyproperty.database

import com.mhdsuhail.ratemyproperty.data.room.*
import org.junit.After
import org.junit.Before

abstract class DatabaseTests {

    protected lateinit var rmpDatabase: RMPDatabase
    protected lateinit var featureDao: FeatureDao
    protected lateinit var descriptionDao: DescriptionsDao
    protected lateinit var searchDao: SearchQueryDao
    protected lateinit var propertyDetailsDao: PropertyDetailsDao
    protected lateinit var propertyDao: PropertyDao

    @Before
    abstract fun setUpDb()

    @After
    abstract fun closeAndClearDb()
}