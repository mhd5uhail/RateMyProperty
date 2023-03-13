package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mhdsuhail.ratemyproperty.data.CanadianProvince
import java.io.IOException
import javax.inject.Inject

class CanadianProvinceParser @Inject constructor() : JsonParser<CanadianProvince> {
        companion object{
            private const val TAG = "CanadianProvinceParser"
        }
        override fun getDataAsList(context: Context, filePath : String): List<CanadianProvince> {
            lateinit var jsonString : String
            try {
                jsonString = context.assets.open(filePath).bufferedReader().use {
                    it.readText()
                }
            }catch (ioException: IOException){
                Log.d(TAG, "parse: failed due to : ${ioException.message}" )
            }
            val listOfData = object : TypeToken<List<CanadianProvince>>() {}.type
            return Gson().fromJson(jsonString,listOfData)
        }
}