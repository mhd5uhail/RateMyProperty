package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.data.AssetRepository
import com.mhdsuhail.ratemyproperty.data.CanadianProvince
import com.mhdsuhail.ratemyproperty.data.FeatureData
import com.mhdsuhail.ratemyproperty.data.UnitType

class PreviewAssetRepository : AssetRepository {
    override fun getCanadianProvinceAndCity(): List<CanadianProvince> {
        return listOf(CanadianProvince(name = "Ontario", emptyList(), emptyList()))
    }

    override fun getStandardFeatures(): List<FeatureData> {
        return listOf(FeatureData(name = "Area", unitTypes = emptyList(), id = 0, description = ""))
    }

    override fun getStandardFeatureUnits(): List<UnitType> {
        return listOf(UnitType(1,"sq ft","Number"))
    }
}