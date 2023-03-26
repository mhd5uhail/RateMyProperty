package com.mhdsuhail.ratemyproperty.database

import android.content.ContentValues
import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.mhdsuhail.ratemyproperty.data.DateTimeTypeConverters
import com.mhdsuhail.ratemyproperty.data.SearchQuery
import com.mhdsuhail.ratemyproperty.data.room.*
import junit.framework.TestCase.*
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.LocalDateTime

class SearchTests : DatabaseTests() {
    private val TAG = "SearchTests"
    private lateinit var searchDao: SearchQueryDao
    @Test
    fun insertSearchItem() = runBlocking {
        val searchQuery = SearchQuery("test", LocalDateTime.now(), 1)
        searchDao.insert(searchQuery)
        val result = searchDao.getSearchQueryById(searchQuery.query)
        assertEquals(result!!.query, searchQuery.query)
        rmpDatabase.clearAllTables()
    }

    @Test
    fun deleteSearchItem() = runBlocking {
        val searchQuery = SearchQuery("test", LocalDateTime.now(), 1)
        searchDao.insert(searchQuery)
        val result = searchDao.getSearchHistory().first()
        assertEquals(result[0].query, searchQuery.query)
        searchDao.delete(result[0])
        val result2 = searchDao.getSearchQueryById(searchQuery.query)
        assertNull(result2)
        rmpDatabase.clearAllTables()
    }

    @Test
    fun searchHistoryListenerTest() = runBlocking {
        val testQuery = SearchQuery("test1", LocalDateTime.now(), 1)
        searchDao.insert(testQuery)
        val result = searchDao.getSearchHistory().first()
        assertEquals(result[0].query,testQuery.query)
    }

    @Before
    override fun setUpDb() {
        Log.i(ContentValues.TAG, "Setting up database")
        val context = ApplicationProvider.getApplicationContext<Context>()
        rmpDatabase = getDbInstance(context)
        searchDao = rmpDatabase.searchQueryDao
    }

    @After
    override fun closeAndClearDb() {
        rmpDatabase.clearAllTables()
        rmpDatabase.close()
    }

}