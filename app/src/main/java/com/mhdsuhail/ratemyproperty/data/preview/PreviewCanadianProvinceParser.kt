package com.mhdsuhail.ratemyproperty.data.preview

import android.content.Context
import com.mhdsuhail.ratemyproperty.data.CanadianProvince
import com.mhdsuhail.ratemyproperty.data.json.JsonParser
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreviewCanadianProvinceParser @Inject constructor(): JsonParser<CanadianProvince> {
    override fun getDataAsList(context: Context, filePath: String): List<CanadianProvince> {
        return listOf(
            CanadianProvince(
                name = "Ontario",
                towns = listOf("Waterloo,London"),
                cities = listOf("Toronto,Ottawa")
            )
        )
    }
}