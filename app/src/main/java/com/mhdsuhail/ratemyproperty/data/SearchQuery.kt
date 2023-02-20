package com.mhdsuhail.ratemyproperty.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.Date

@Entity(tableName = "search_history")
data class SearchQuery(
    @PrimaryKey
    val query: String,
    val date: LocalDateTime?,
    val resultCount: Int
)