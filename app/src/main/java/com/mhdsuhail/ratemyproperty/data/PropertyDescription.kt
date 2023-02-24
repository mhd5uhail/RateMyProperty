package com.mhdsuhail.ratemyproperty.data

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "descriptions" , primaryKeys = ["id"])
data class PropertyDescription(
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "prop_uri") val prop_uri: String,
    @ColumnInfo(name = "text") val text: String
)