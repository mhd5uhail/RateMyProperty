package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

//TODO: Looking for updates on Issue: Gson deserializes wildcards to LinkedHashMap #1107:
// https://github.com/google/gson/issues/1107
class AssetJsonParser @Inject constructor() {
    val TAG = "AssetJsonParser"

    inline fun <reified T> getDataAsList(context: Context, filePath : String): List<T> {
        lateinit var jsonString : String
        try {
            jsonString = context.assets.open(filePath).bufferedReader().use {
                it.readText()
            }
        }catch (ioException: IOException){
            Log.d(TAG, "parse: failed due to : ${ioException.message}" )
        }
        val listOfData = object : TypeToken<List<T>>() {}.type
        return Gson().fromJson(jsonString,listOfData)
    }
}