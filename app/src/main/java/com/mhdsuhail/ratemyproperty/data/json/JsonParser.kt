package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import com.mhdsuhail.ratemyproperty.data.CanadianProvince

interface JsonParser<T> {
    fun getDataAsList(context: Context,filePath: String): List<T>
}