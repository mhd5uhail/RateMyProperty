package com.mhdsuhail.ratemyproperty.data

import com.mhdsuhail.ratemyproperty.data.room.PropertyWithExtraInfoDao

class PropertyWithExtraInfoRepo(private val dao: PropertyWithExtraInfoDao) {

    fun getPropertyWithExtraInfoById(uri: String) : PropertyWithExtraInfo? {
        return dao.getPropertyWithExtraInfoById(uri)
    }

}