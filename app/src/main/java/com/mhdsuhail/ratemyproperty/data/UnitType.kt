package com.mhdsuhail.ratemyproperty.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UnitType(
    @SerializedName("id")  @Expose val id: Int,
    @SerializedName("unit") @Expose val unit: String,
    @SerializedName("type") @Expose val type: String
){
    companion object{
        enum class Types {
            Number,
            Boolean,
        }
    }
}