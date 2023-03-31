package com.mhdsuhail.ratemyproperty.data


interface AssetRepository {
    fun getCanadianProvinceAndCity() : List<CanadianProvince>

    fun getStandardFeatures() : List<FeatureData>

    fun getStandardFeatureUnits() : List<UnitType>

    fun getProvince2CityMap(): Map<String,List<String>>

    fun getFeature2UnitMap(): Map<String, List<String>>
}