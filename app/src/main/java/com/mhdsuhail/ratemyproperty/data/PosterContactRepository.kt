package com.mhdsuhail.ratemyproperty.data

interface PosterContactRepository {
    suspend fun insertPosterContact(contact: PosterContact)

    suspend fun deletePosterContact(contact: PosterContact)

    suspend fun getPosterContactById(id: Int): PosterContact?

    suspend fun getPosterContactByPropId(id: Int): PosterContact?

}