package com.mhdsuhail.ratemyproperty.data.json

import android.content.Context
import android.content.res.AssetManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import javax.inject.Inject

// https://github.com/google/gson/issues/1107
class AssetJsonParserImpl<T> @Inject constructor() {
    val TAG = "AssetJsonParserImpl"

    fun getDataAsList(assetManager: AssetManager, filePath : String, typeToken: TypeToken<T>): List<T> {
        lateinit var jsonString : String
        try {
            jsonString = assetManager.open(filePath).bufferedReader().use {
                it.readText()
            }
        }catch (ioException: IOException){
            Log.d(TAG, "parse: failed due to : ${ioException.message}" )
        }

        val listOfData = TypeToken.getParameterized(List::class.java,typeToken.type).type
        return Gson().fromJson(jsonString,listOfData)
    }
}