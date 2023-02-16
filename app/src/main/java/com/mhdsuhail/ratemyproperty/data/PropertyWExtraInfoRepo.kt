package com.mhdsuhail.ratemyproperty.data

interface PropertyWExtraInfoRepo {
    fun getPropertyWithExtraInfoById(uri: String): PropertyWithExtraInfo?
}