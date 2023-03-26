package com.mhdsuhail.ratemyproperty.database

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mhdsuhail.ratemyproperty.data.AddressTypeConverter
import com.mhdsuhail.ratemyproperty.data.DateTimeTypeConverters
import com.mhdsuhail.ratemyproperty.data.room.*
import org.junit.After
import org.junit.Before

abstract class DatabaseTests {

    protected lateinit var rmpDatabase: RMPDatabase
    fun getDbInstance(context: Context) : RMPDatabase{
        return Room.inMemoryDatabaseBuilder(context, RMPDatabase::class.java)
            .addTypeConverter(DateTimeTypeConverters())
            .addTypeConverter(AddressTypeConverter())
            .fallbackToDestructiveMigration().build()
    }

    @Before
    abstract fun setUpDb()

    @After
    abstract fun closeAndClearDb()
}