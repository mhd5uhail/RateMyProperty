package com.mhdsuhail.ratemyproperty.data

import androidx.room.TypeConverter

class AddressTypeConverter {

    @TypeConverter
    fun toString(address: Address): String {
        return address.let {
            " ${address.unitNum} ${address.street} ${address.city} ${address.state} ${address.country} ${address.postalCode}"
        }
    }

}