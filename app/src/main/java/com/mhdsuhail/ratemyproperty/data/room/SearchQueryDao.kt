package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*
import com.mhdsuhail.ratemyproperty.data.SearchQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchQueryDao : BaseDao<SearchQuery>{

    @Query("SELECT COUNT(*) FROM search_history")
    suspend fun getCountOfSearches() : Int

    @Query("SELECT * FROM search_history WHERE query = :query")
    suspend fun getSearchQueryById(query: String): SearchQuery?

    @Query("SELECT * FROM search_history")
    fun getSearchHistory(): Flow<List<SearchQuery>>

}