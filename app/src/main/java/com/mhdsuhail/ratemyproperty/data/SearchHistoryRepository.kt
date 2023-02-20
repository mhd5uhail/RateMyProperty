package com.mhdsuhail.ratemyproperty.data
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {

    suspend fun insertSearchQuery(searchQuery: SearchQuery)

    suspend fun deleteSearchQuery(searchQuery: SearchQuery)

    suspend fun getCountOfSearches() : Int

    suspend fun getSearchQueryById(query: String): SearchQuery?

    fun getSearchHistory(): Flow<List<SearchQuery>>

}