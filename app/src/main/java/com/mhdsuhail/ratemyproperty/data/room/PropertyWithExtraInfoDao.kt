package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mhdsuhail.ratemyproperty.data.PropertyWithExtraInfo

@Dao
interface PropertyWithExtraInfoDao {

    @Transaction
    @Query("SELECT * FROM property WHERE uri = :uri")
    fun getPropertyWithExtraInfoById(uri: String) : PropertyWithExtraInfo?

}