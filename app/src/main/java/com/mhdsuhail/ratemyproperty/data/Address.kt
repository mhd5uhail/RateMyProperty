package com.mhdsuhail.ratemyproperty.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
class Address(
    @SerializedName("country") @Expose var country: String,
    @SerializedName("province") @Expose var province: String,
    @SerializedName("city") @Expose var city: String,
    @SerializedName("street") @Expose var street: String,
    @SerializedName("unitNum") @Expose var unitNum: String,
    @SerializedName("postalCode") @Expose var postalCode: String,
) {
    override fun toString(): String {
        return "${this.unitNum} ${this.street} ${this.city} ${this.province} ${this.country} ${this.postalCode}"
    }
}