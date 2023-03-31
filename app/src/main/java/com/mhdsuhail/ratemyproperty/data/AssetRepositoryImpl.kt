package com.mhdsuhail.ratemyproperty.data

import android.content.res.AssetManager
import com.google.gson.reflect.TypeToken
import com.mhdsuhail.ratemyproperty.data.json.AssetJsonParserImpl


class AssetRepositoryImpl(private val assetManager: AssetManager) : AssetRepository {

    companion object {
        private const val TAG = "AssetRepositoryImpl"
        private const val GEO_DATA_FILE = "geo-data/canada_states_cities.json"
        private const val FEATURE_DATA_FILE = "features/property_features.json"
        private const val UNIT_TYPES_FILE = "features/unit_types.json"
    }

    override fun getCanadianProvinceAndCity(): List<CanadianProvince> {

        return AssetJsonParserImpl<CanadianProvince>().getDataAsList(assetManager = assetManager,
            GEO_DATA_FILE,
            object : TypeToken<CanadianProvince>() {})
    }

    override fun getStandardFeatures(): List<FeatureData> {
        return AssetJsonParserImpl<FeatureData>().getDataAsList(assetManager = assetManager,
            FEATURE_DATA_FILE,
            object : TypeToken<FeatureData>() {})
    }

    override fun getStandardFeatureUnits(): List<UnitType> {
        return AssetJsonParserImpl<UnitType>().getDataAsList(assetManager = assetManager,
            UNIT_TYPES_FILE,
            object : TypeToken<UnitType>() {})
    }

    override fun getProvince2CityMap(): Map<String,List<String>> {
        val province2City = HashMap<String,List<String>>()
        getCanadianProvinceAndCity().forEach { province ->
            province2City[province.name!!] = province.cities + province.towns
        }
        return province2City
    }

    override fun getFeature2UnitMap(): Map<String, List<String>> {

        val unitMap = HashMap<Int, UnitType>()
        getStandardFeatureUnits().forEach { unit ->
            unitMap[unit.id] = unit
        }
        val units2Feature =  HashMap<String,List<String>>();

        getStandardFeatures().forEach { featureUnitData ->
            units2Feature[featureUnitData.name] = let {
                val units = ArrayList<String>()
                featureUnitData.unitTypes.forEach { unit ->
                    unitMap[unit]?.let { it -> units.add(it.unit) }
                }
                units
            }
        }
        return units2Feature
    }



}