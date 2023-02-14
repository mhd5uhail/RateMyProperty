package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.AddressDao

class AddressRepositoryImpl(private val dao: AddressDao) : AddressRepository {
    override suspend fun insertAddress(address: Address) {
        dao.insertAddress(address)
    }

    override suspend fun deleteAddress(address: Address) {
        dao.deleteAddress(address)
    }

    override suspend fun getAddressById(id: Int): Address? {
        return dao.getAddressById(id)
    }

    override suspend fun getAddressByPropId(id: Int): Address? {
        return dao.getAddressByPropId(id)
    }
}