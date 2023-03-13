package com.mhdsuhail.ratemyproperty.data.preview

import android.content.Context
import com.mhdsuhail.ratemyproperty.data.UnitType
import com.mhdsuhail.ratemyproperty.data.json.JsonParser

class PreviewUnitTypeParser : JsonParser<UnitType> {
    override fun getDataAsList(context: Context, filePath: String): List<UnitType> {
        return listOf(
            UnitType(1, "sq ft", "Number"),
            UnitType(2, "Swimming Pool", "Boolean")
        )
    }
}