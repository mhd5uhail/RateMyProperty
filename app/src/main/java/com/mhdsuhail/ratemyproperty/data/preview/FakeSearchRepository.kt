package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.data.SearchHistoryRepository
import com.mhdsuhail.ratemyproperty.data.SearchQuery
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime

class FakeSearchRepository : SearchHistoryRepository {
    override suspend fun insertSearchQuery(searchQuery: SearchQuery) {
        //TODO("Not yet implemented")
    }

    override suspend fun deleteSearchQuery(searchQuery: SearchQuery) {
        //TODO("Not yet implemented")
    }

    override suspend fun getCountOfSearches(): Int {
        return 0
        //TODO("Not yet implemented")
    }

    override suspend fun getSearchQueryById(query: String): SearchQuery? {
        //TODO("Not yet implemented")
        return null
    }

    override fun getSearchHistory(): Flow<List<SearchQuery>> {
        return flow{
            while(true){
                emit(listOf(SearchQuery("Test 1", LocalDateTime.now(),0)))
                delay(1000)
            }
        }
    }
}