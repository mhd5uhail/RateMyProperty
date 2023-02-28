package com.mhdsuhail.ratemyproperty.database

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.preview.FeaturePreviewProvider
import com.mhdsuhail.ratemyproperty.data.preview.PropertySampleData
import com.mhdsuhail.ratemyproperty.data.room.*
import junit.framework.TestCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest : TestCase() {

    private lateinit var rmpDatabase: RMPDatabase
    private lateinit var featureDao: FeatureDao
    private lateinit var descriptionDao : DescriptionsDao
    private lateinit var searchDao: SearchQueryDao
    private lateinit var propertyDetailsDao: PropertyDetailsDao
    private lateinit var propertyDao: PropertyDao



    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = Room.inMemoryDatabaseBuilder(context, RMPDatabase::class.java)
            .addTypeConverter(DateTimeTypeConverters()).fallbackToDestructiveMigration().build()
        searchDao = rmpDatabase.searchQueryDao
        featureDao = rmpDatabase.featureDao
        descriptionDao = rmpDatabase.descriptionsDao
        propertyDetailsDao = rmpDatabase.propertyDetailsDao
        propertyDao = rmpDatabase.propertyDao
        Log.i(TAG, "createDb")
    }

    @After
    fun closeDb() {
        rmpDatabase.close()
        Log.i(TAG, "closeDb")
    }

    @Test
    fun insertPropertyDetails() = runBlocking {
        val propertyDetails = PropertySampleData().sample.propertyDetails

        propertyDetailsDao.insert(propertyDetails)

        val result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertEquals(result!!.uri,propertyDetails.uri)
    }

    @Test
    fun propertyRelationTest() = runBlocking {
        val propertyDetails =  PropertySampleData().sample.propertyDetails
        val features =  PropertySampleData().sample.features
        val desc =  PropertySampleData().sample.description

        propertyDetailsDao.insert(propertyDetails)
        featureDao.insertAll(features)
        descriptionDao.insert(desc)

        val result = propertyDao.getPropertyById(propertyDetails.uri)
        assertNotNull(result)
        if (result != null) {
            assertEquals(result.propertyDetails.uri,propertyDetails.uri)
            assertTrue(result.features.containsAll(features))
            assertEquals(result.description.prop_uri,desc.prop_uri)
        }
    }

    @Test
    fun insertSearchItem() = runBlocking {

        val searchQuery = SearchQuery("test", LocalDateTime.now(), 1)
        searchDao.insert(searchQuery)
        val result = searchDao.getSearchHistory().first()
        assertEquals(result[0].query,searchQuery.query)

    }


}