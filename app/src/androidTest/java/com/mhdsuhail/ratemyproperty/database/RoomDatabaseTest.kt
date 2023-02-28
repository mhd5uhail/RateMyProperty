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

    private val dummyProperty =  Property(
                propertyDetails = PropertyDetails(
                uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
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
            description = PropertyDescription(prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis finibus sem. Duis nec dolor et tortor malesuada pellentesque. Suspendisse porttitor tempus lectus, non commodo orci rhoncus et. Praesent odio est, ultricies sed augue ut, laoreet congue magna. Duis semper suscipit bibendum. Maecenas semper dolor vel nulla congue dignissim. Ut pretium lobortis felis a tristique\n")
    )

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = Room.inMemoryDatabaseBuilder(context, RMPDatabase::class.java)
            .addTypeConverter(DateTimeTypeConverters()).fallbackToDestructiveMigration().build()
        searchDao = rmpDatabase.searchQueryDao
        featureDao = rmpDatabase.featureDao
        descriptionDao = rmpDatabase.descriptionsDao
        propertyDetailsDao = rmpDatabase.propertyDetailsDao
        Log.i(TAG, "createDb")
    }

    @After
    fun closeDb() {
        rmpDatabase.close()
        Log.i(TAG, "closeDb")
    }

    @Test
    fun insertPropertyDetails() = runBlocking {
        val propertyDetails = dummyProperty.propertyDetails

        propertyDetailsDao.insert(propertyDetails)

        val result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertEquals(result!!.uri,propertyDetails.uri)
    }

    @Test
    fun propertyRelationTest() = runBlocking {
        val propertyDetails = dummyProperty.propertyDetails
        val features = dummyProperty.features
        val desc = dummyProperty.description

        propertyDetailsDao.insert(propertyDetails)
        featureDao.insertAll(features)
        descriptionDao.insert(desc)

        val result = propertyDetailsDao.getPropertyById(propertyDetails.uri)
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