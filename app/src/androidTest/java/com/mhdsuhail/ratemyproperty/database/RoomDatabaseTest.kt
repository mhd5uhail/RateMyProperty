package com.mhdsuhail.ratemyproperty.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.data.preview.FeaturePreviewProvider
import com.mhdsuhail.ratemyproperty.data.room.PropertyDao
import com.mhdsuhail.ratemyproperty.data.room.RMPDatabase
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDatabaseTest : TestCase() {

    private lateinit var rmpDatabase: RMPDatabase
    private lateinit var propertyDao: PropertyDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = Room.inMemoryDatabaseBuilder(context,RMPDatabase::class.java).build()
        propertyDao = rmpDatabase.propertyDao
    }

    @Test
    suspend fun insertRoomItem(){
        val property = Property(
            propertyDetails = PropertyDetails(
                uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",
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
            description = PropertyDescription(prop_uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis finibus sem. Duis nec dolor et tortor malesuada pellentesque. Suspendisse porttitor tempus lectus, non commodo orci rhoncus et. Praesent odio est, ultricies sed augue ut, laoreet congue magna. Duis semper suscipit bibendum. Maecenas semper dolor vel nulla congue dignissim. Ut pretium lobortis felis a tristique\n")
        )
        propertyDao.insertProperty(property)
    }


    @After
    fun closeDb(){
        rmpDatabase.clearAllTables()
        rmpDatabase.close()
    }
}