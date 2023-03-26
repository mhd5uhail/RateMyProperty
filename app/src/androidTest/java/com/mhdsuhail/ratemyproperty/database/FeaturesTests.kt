package com.mhdsuhail.ratemyproperty.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mhdsuhail.ratemyproperty.data.DateTimeTypeConverters
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.preview.PropertySampleData
import com.mhdsuhail.ratemyproperty.data.room.*
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class FeaturesTests : DatabaseTests() {
    private val TAG = "FeaturesTests"
    private lateinit var featureDao: FeatureDao
    private lateinit var propertyDetailsDao: PropertyDetailsDao
    @Before
    override fun setUpDb() {
        Log.i(TAG, "Setting up database")
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = getDbInstance(context)
        featureDao = rmpDatabase.featureDao
        propertyDetailsDao = rmpDatabase.propertyDetailsDao
    }

    @Test
    fun insertFeatureWithoutExistingParentProperty()  {
        val testFeature = Feature(
            prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
            name = "Area",
            imageResource = null,
            unit = "sqft",
            value = "10",
            description = null
        )
        assertThrows(SQLiteConstraintException::class.java) {
            runBlocking {
                featureDao.insert(testFeature)
            }
        }
    }

    @Test
    fun insertOneFeature() = runBlocking{
        val testFeature = Feature(
            prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
            name = "Area",
            imageResource = null,
            unit = "sqft",
            value = "10",
            description = null
        )
        val propertyDetails = PropertySampleData().sample.propertyDetails

        rmpDatabase.runInTransaction {
            runBlocking {
                propertyDetailsDao.insert(propertyDetails)
                featureDao.insert(testFeature)
            }
        }
        var result: List<Feature> = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")

        assertEquals(1,result.size)
        assertEquals(testFeature.prop_uri,result[0].prop_uri,)
        rmpDatabase.clearAllTables()
    }

    @Test
    fun insertMultipleFeature() = runBlocking {
        val testFeatures = listOf(
            Feature(
                prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
                name = "Area",
                imageResource = null,
                unit = "sqft",
                value = "10",
                description = null
            ), Feature(
                prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
                name = "Parking",
                imageResource = null,
                unit = "spots",
                value = "2",
                description = null
            )
        )
        val propertyDetails = PropertySampleData().sample.propertyDetails

        rmpDatabase.runInTransaction {
            runBlocking {
                propertyDetailsDao.insert(propertyDetails)
                featureDao.insertAll(testFeatures)
            }
        }
        var result: List<Feature> = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")

        assertEquals(2,result.size)

        testFeatures.forEach { testFeature ->
            assertTrue(result.any { resultFeature -> resultFeature.name == testFeature.name })
        }
        rmpDatabase.clearAllTables()
    }

    // Delete property and see if features are deleted
    @Test
    fun deleteOneFeature() = runBlocking {
        val testFeature = Feature(
            prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
            name = "Area",
            imageResource = null,
            unit = "sqft",
            value = "10",
            description = null
        )
        val propertyDetails = PropertySampleData().sample.propertyDetails

        rmpDatabase.runInTransaction {
            runBlocking {
                propertyDetailsDao.insert(propertyDetails)
                featureDao.insert(testFeature)
            }
        }
        var result: List<Feature> = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")

        assertEquals( 1,result.size,)
        assertEquals( testFeature.prop_uri,result[0].prop_uri)

        featureDao.delete(result[0])
        result = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")

        assertEquals(0,result.size)

        rmpDatabase.clearAllTables()
    }


    @Test
    fun deleteParentProperty() = runBlocking{
        val testFeatures = listOf(
            Feature(
                prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
                name = "Area",
                imageResource = null,
                unit = "sqft",
                value = "10",
                description = null
            ), Feature(
                prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
                name = "Parking",
                imageResource = null,
                unit = "spots",
                value = "2",
                description = null
            )
        )
        val propertyDetails = PropertySampleData().sample.propertyDetails

        rmpDatabase.runInTransaction {
            runBlocking {
                propertyDetailsDao.insert(propertyDetails)
                featureDao.insertAll(testFeatures)
            }
        }
        var result: List<Feature> = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")

        assertEquals(2,result.size)

        testFeatures.forEach { testFeature ->
            assertTrue(result.any { resultFeature -> resultFeature.name == testFeature.name })
        }

        propertyDetailsDao.delete(propertyDetails)
        result = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")

        assertEquals(0,result.size)

        rmpDatabase.clearAllTables()
    }


    @After
    override fun closeAndClearDb() {
        rmpDatabase.close()
    }
}