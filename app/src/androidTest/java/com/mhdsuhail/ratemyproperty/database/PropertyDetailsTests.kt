package com.mhdsuhail.ratemyproperty.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mhdsuhail.ratemyproperty.data.AddressTypeConverter
import com.mhdsuhail.ratemyproperty.data.DateTimeTypeConverters
import com.mhdsuhail.ratemyproperty.data.preview.PropertySampleData
import com.mhdsuhail.ratemyproperty.data.room.*
import junit.framework.TestCase.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class PropertyDetailsTests : DatabaseTests() {
    private val TAG = "PropertyDetailsTests"

    @Before
    override fun setUpDb() {

        Log.i(ContentValues.TAG, "Setting up database")
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = Room.inMemoryDatabaseBuilder(context, RMPDatabase::class.java)
            .addTypeConverter(DateTimeTypeConverters()).fallbackToDestructiveMigration().build()
        featureDao = rmpDatabase.featureDao
        descriptionDao = rmpDatabase.descriptionsDao
        propertyDetailsDao = rmpDatabase.propertyDetailsDao
        propertyDao = rmpDatabase.propertyDao
    }

    @After
    override fun closeAndClearDb() {
        rmpDatabase.close()
        Log.i(ContentValues.TAG, "closeDb")
    }


    @Test
    fun insert_PropertyDetails() = runBlocking {
        val propertyDetails = PropertySampleData().sample.propertyDetails

        propertyDetailsDao.insert(propertyDetails)

        val result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertEquals(result!!.uri, propertyDetails.uri)
    }

    @Test
    fun insertDuplicate_PropertyDetails() = runBlocking {
        val propertyDetails = PropertySampleData().sample.propertyDetails

        propertyDetailsDao.insert(propertyDetails)

        var result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertEquals(result!!.uri, propertyDetails.uri)

        val duplicateProp = propertyDetails.copy(price = 1243)
        propertyDetailsDao.insert(duplicateProp)

        result = propertyDetailsDao.getPropertyDetails(duplicateProp.uri)
        assertEquals(result!!.uri, duplicateProp.uri)
        assertEquals(result.price, duplicateProp.price)
    }

    @Test
    fun insertMultiple_PropertyDetails() = runBlocking {
        val testList = listOf(
            PropertySampleData().sample.propertyDetails,
            PropertySampleData().sample.propertyDetails.copy(uri = "testUri")
        )
        propertyDetailsDao.insertAll(testList)
        val result = propertyDetailsDao.getAllPropertiesDetails().first()

        assertEquals(result.size, testList.size)
        testList.forEach { item ->
            assertTrue(result.any { resultProp -> resultProp.uri == item.uri })
        }
    }


    @Test
    fun delete_PropertyDetails() = runBlocking {
        val propertyDetails = PropertySampleData().sample.propertyDetails

        propertyDetailsDao.insert(propertyDetails)

        var result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertEquals(result!!.uri, propertyDetails.uri)

        propertyDetailsDao.delete(result)
        result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertNull(result)
    }

    @Test
    fun update_PropertyDetails() = runBlocking {
        val propertyDetails = PropertySampleData().sample.propertyDetails

        propertyDetailsDao.insert(propertyDetails)

        var result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)
        assertEquals(result!!.uri, propertyDetails.uri)
        assertEquals(
            AddressTypeConverter().toString(result.address),
            AddressTypeConverter().toString(propertyDetails.address)
        )
        result.address.state = "Montreal"
        propertyDetailsDao.update(result)

        result = propertyDetailsDao.getPropertyDetails(propertyDetails.uri)

        val newAddress = AddressTypeConverter().toString(result!!.address);
        val oldAddress = AddressTypeConverter().toString(propertyDetails.address)
        Log.i(TAG, "update_PropertyDetails: $newAddress || $oldAddress")
        assertTrue(oldAddress != newAddress)
    }


    @Test
    fun get_propertyRelationTest() = runBlocking {
        val propertyDetails = PropertySampleData().sample.propertyDetails
        val features = PropertySampleData().sample.features
        val desc = PropertySampleData().sample.description

        rmpDatabase.runInTransaction(Runnable {
            runBlocking {
                propertyDetailsDao.insert(propertyDetails)
                featureDao.insertAll(features)
                descriptionDao.insert(desc)
            }
        })

        val result = propertyDao.getPropertyById(propertyDetails.uri)
        assertNotNull(result)
        if (result != null) {
            assertEquals(result.propertyDetails.uri, propertyDetails.uri)
            assertTrue(result.features.containsAll(features))
            assertEquals(result.description.prop_uri, desc.prop_uri)
        }
    }


}