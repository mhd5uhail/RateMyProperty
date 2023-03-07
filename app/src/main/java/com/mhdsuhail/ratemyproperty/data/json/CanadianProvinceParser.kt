package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mhdsuhail.ratemyproperty.data.CanadianProvince
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CanadianProvinceParser @Inject constructor() : JsonParser<CanadianProvince> {
    private val TAG = "CanadianProvinceParser"
    override fun getData(context: Context): List<CanadianProvince> {

        lateinit var jsonString : String
        try {
            jsonString = context.assets.open("geodata/canada_states_cities.json").bufferedReader().use {
                it.readText()
            }
        }catch (ioException: IOException){
            Log.d(TAG, "parse: failed due to : ${ioException.message}" )
        }
        val listOfData = object : TypeToken<List<CanadianProvince>>() {}.type
        return Gson().fromJson(jsonString,listOfData)
    }
}