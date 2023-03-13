package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mhdsuhail.ratemyproperty.data.UnitType
import java.io.IOException
import javax.inject.Inject

class UnitTypeDataParser @Inject constructor() : JsonParser<UnitType> {
    companion object {
        private const val TAG = "UnitTypeDataParser"
    }

    override fun getDataAsList(context: Context, filePath: String): List<UnitType> {
        lateinit var jsonString: String
        try {
            jsonString = context.assets.open(filePath).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            Log.d(TAG, "parse: failed due to : ${ioException.message}")
        }
        val listOfData = object : TypeToken<List<UnitType>>() {}.type
        return Gson().fromJson(jsonString, listOfData)
    }
}
