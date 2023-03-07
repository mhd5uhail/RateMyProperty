package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import com.mhdsuhail.ratemyproperty.data.CanadianProvince

interface JsonParser<T> {
    fun getData(context: Context): List<T>
}