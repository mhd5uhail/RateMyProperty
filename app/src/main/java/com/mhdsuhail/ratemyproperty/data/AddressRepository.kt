package com.mhdsuhail.ratemyproperty.data

interface AddressRepository {

    suspend fun insertAddress(address: Address)

    suspend fun deleteAddress(address: Address)

    suspend fun getAddressById(id: Int): Address?

    suspend fun getAddressByPropId(id: Int): Address?
}