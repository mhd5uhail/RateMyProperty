//package com.mhdsuhail.ratemyproperty.data.json
//
//import android.content.Context
//import android.util.Log
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import com.mhdsuhail.ratemyproperty.data.FeatureData
//import java.io.IOException
//import javax.inject.Inject
//
//class FeatureDataParser @Inject constructor() :  JsonParser<FeatureData> {
//    companion object {
//        private const val TAG = "FeatureDataParser"
//    }
//
//    override fun getDataAsList(context: Context, filePath: String): List<FeatureData> {
//        lateinit var jsonString: String
//        try {
//            jsonString = context.assets.open(filePath).bufferedReader().use {
//                it.readText()
//            }
//        } catch (ioException: IOException) {
//            Log.d(TAG, "parse: failed due to : ${ioException.message}")
//        }
//        val listOfData = object : TypeToken<List<FeatureData>>() {}.type
//        return Gson().fromJson(jsonString, listOfData)
//    }
//}