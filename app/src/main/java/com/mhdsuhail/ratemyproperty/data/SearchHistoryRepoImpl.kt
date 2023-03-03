package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.SearchQueryDao
import kotlinx.coroutines.flow.Flow

class SearchHistoryRepoImpl(private val dao: SearchQueryDao) : SearchHistoryRepository {
    override suspend fun insertSearchQuery(searchQuery: SearchQuery) {
        dao.insert(searchQuery)
    }

    override suspend fun deleteSearchQuery(searchQuery: SearchQuery) {
        dao.delete(searchQuery)
    }

    override suspend fun getCountOfSearches(): Int {
        return dao.getCountOfSearches()
    }

    override suspend fun getSearchQueryById(query: String): SearchQuery? {
        return dao.getSearchQueryById(query)
    }

    override fun getSearchHistory(): Flow<List<SearchQuery>> {
        return dao.getSearchHistory()
    }
}