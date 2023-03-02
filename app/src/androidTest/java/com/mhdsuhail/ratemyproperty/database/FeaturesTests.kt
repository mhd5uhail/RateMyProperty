package com.mhdsuhail.ratemyproperty.database

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mhdsuhail.ratemyproperty.data.DateTimeTypeConverters
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.preview.PropertySampleData
import com.mhdsuhail.ratemyproperty.data.room.RMPDatabase
import junit.framework.TestCase.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertThrows
import org.junit.Before
import org.junit.Test

class FeaturesTests : DatabaseTests() {

    @Before
    override fun setUpDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = Room.inMemoryDatabaseBuilder(context, RMPDatabase::class.java)
            .addTypeConverter(DateTimeTypeConverters()).fallbackToDestructiveMigration().build()
        featureDao = rmpDatabase.featureDao
        propertyDetailsDao = rmpDatabase.propertyDetailsDao
    }

    @Test
    fun insertFeatureWithoutExistingParentProperty() {
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
    fun insertOneFeature() {
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
        var result: List<Feature>
        runBlocking {
            result = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")
        }
        assertEquals(result.size, 1)
        assertEquals(result[0].prop_uri, testFeature.prop_uri)
    }

    @Test
    fun insertMultipleFeature() {
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
        var result: List<Feature>
        runBlocking {
            result = featureDao.getFeatureByPropId("59ac0c32-cc0e-49f9-a881-c0bd073f11cd")
        }
        assertEquals(result.size, 2)

        testFeatures.forEach { testFeature ->
            assertTrue(result.any { resultFeature -> resultFeature.name == testFeature.name })
        }
    }

    @After
    override fun closeAndClearDb() {
        rmpDatabase.close()
    }
}