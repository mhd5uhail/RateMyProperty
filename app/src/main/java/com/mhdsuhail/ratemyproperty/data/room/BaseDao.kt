package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    suspend fun insertAll(obj: List<T>) : List<Long>

    @Update
    suspend fun update(obj: T) : Int

    @Delete
    suspend fun delete(obj: T) : Int
}