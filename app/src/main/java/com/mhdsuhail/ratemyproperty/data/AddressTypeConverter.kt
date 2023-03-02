package com.mhdsuhail.ratemyproperty.data

import androidx.room.TypeConverter

class AddressTypeConverter {

    @TypeConverter
    fun toString(address: Address): String {
        return address.let {
            " ${address.unitNum} ${address.street} ${address.city} ${address.state} ${address.country} ${address.postalCode}"
        }
    }

    @TypeConverter
    fun fromString(address: String): Address? {
        // "Regex based address converter to re-create the address object")
        TODO("Not yet Implemented")
    }

}