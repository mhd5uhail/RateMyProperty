package com.mhdsuhail.ratemyproperty.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FeatureData(
    @SerializedName("id") @Expose var id: Int,
    @SerializedName("name") @Expose var name: String,
    @SerializedName("unitTypes") @Expose var unitTypes: List<Int>,
    @SerializedName("description") @Expose var description: String
)
