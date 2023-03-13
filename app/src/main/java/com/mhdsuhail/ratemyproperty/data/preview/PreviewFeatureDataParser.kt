package com.mhdsuhail.ratemyproperty.data.preview

import android.content.Context
import com.mhdsuhail.ratemyproperty.data.FeatureData
import com.mhdsuhail.ratemyproperty.data.json.JsonParser

class PreviewFeatureDataParser : JsonParser<FeatureData> {
    override fun getDataAsList(context: Context, filePath: String): List<FeatureData> {
        return listOf(
            FeatureData(1, "Test1", listOf(1, 2, 3), ""),
            FeatureData(1, "Test2", listOf(4, 5, 6), "")
        )
    }
}