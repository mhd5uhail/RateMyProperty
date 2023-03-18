package com.mhdsuhail.ratemyproperty.data


interface AssetRepository {
    fun getCanadianProvinceAndCity() : List<CanadianProvince>

    fun getStandardFeatures() : List<FeatureData>

    fun getStandardFeatureUnits() : List<UnitType>
}