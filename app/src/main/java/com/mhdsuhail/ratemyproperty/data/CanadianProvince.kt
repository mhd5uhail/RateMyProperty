package com.mhdsuhail.ratemyproperty.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CanadianProvince(
    @SerializedName("Province") @Expose var name: String?,
    @SerializedName("City") @Expose var towns: List<String>,
    @SerializedName("Town") @Expose var cities: List<String>
)