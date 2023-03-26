package com.mhdsuhail.ratemyproperty.data

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
@ProvidedTypeConverter
class AddressTypeConverter {

    @TypeConverter
    fun toString(address: Address?): String? {
        return address.let {
            Gson().toJson(address)
        }
    }

    @TypeConverter
    fun fromString(jsonString: String?): Address? {
        return Gson().fromJson(jsonString,object :TypeToken<Address>() {})
    }

}