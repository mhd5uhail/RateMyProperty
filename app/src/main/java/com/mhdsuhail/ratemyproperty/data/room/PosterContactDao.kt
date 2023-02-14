package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mhdsuhail.ratemyproperty.data.PosterContact

@Dao
interface PosterContactDao {

    @Insert
    suspend fun insertContact(posterContact: PosterContact)

    @Delete
    suspend fun deleteContact(posterContact: PosterContact)

    @Query("SELECT * FROM contacts id = :id")
    suspend fun getContactById(id: Int): PosterContact?

    @Query("SELECT * FROM contacts propId = :id")
    suspend fun getContactByPropId(id: Int) : PosterContact?

}