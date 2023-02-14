package com.mhdsuhail.ratemyproperty.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mhdsuhail.ratemyproperty.data.Address

@Dao
interface AddressDao {
    @Insert
    suspend fun insertAddress(address: Address)

    @Delete
    suspend fun deleteAddress(address: Address)

    @Query("SELECT * FROM addresses id = :id")
    suspend fun getAddressById(id: Int): Address?

    @Query("SELECT * FROM addresses propId = :id")
    suspend fun getAddressByPropId(id: Int): Address?

}