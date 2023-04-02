package com.mhdsuhail.ratemyproperty.data

import android.net.Uri
import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class UriTypeConverter {

    @TypeConverter
    fun toString(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun fromString(str: String?): Uri? {
        return Uri.parse(str)
    }
}