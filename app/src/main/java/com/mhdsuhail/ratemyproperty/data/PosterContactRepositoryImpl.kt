package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.PosterContactDao

class PosterContactRepositoryImpl(private val dao: PosterContactDao) : PosterContactRepository {
    override suspend fun insertPosterContact(contact: PosterContact) {
        dao.insertContact(contact)
    }

    override suspend fun deletePosterContact(contact: PosterContact) {
        dao.deleteContact(contact)
    }

    override suspend fun getPosterContactById(id: Int): PosterContact? {
        return dao.getContactById(id)
    }

    override suspend fun getPosterContactByPropId(id: Int): PosterContact? {
        return dao.getContactByPropId(id)
    }
}