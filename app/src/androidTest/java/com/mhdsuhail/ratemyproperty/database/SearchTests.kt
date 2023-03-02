package com.mhdsuhail.ratemyproperty.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mhdsuhail.ratemyproperty.data.DateTimeTypeConverters
import com.mhdsuhail.ratemyproperty.data.SearchQuery
import com.mhdsuhail.ratemyproperty.data.room.RMPDatabase
import junit.framework.TestCase
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class SearchTests : DatabaseTests() {

    @Test
    fun insertSearchItem() = runBlocking {
        val searchQuery = SearchQuery("test", LocalDateTime.now(), 1)
        searchDao.insert(searchQuery)
        val result = searchDao.getSearchHistory().first()
        TestCase.assertEquals(result[0].query, searchQuery.query)

    }

    @Before
    override fun setUpDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = Room.inMemoryDatabaseBuilder(context, RMPDatabase::class.java)
            .addTypeConverter(DateTimeTypeConverters()).fallbackToDestructiveMigration().build()
        searchDao = rmpDatabase.searchQueryDao
    }

    @After
    override fun closeAndClearDb() {
        rmpDatabase.close()
    }

}