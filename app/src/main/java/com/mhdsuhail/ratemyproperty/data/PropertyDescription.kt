package com.mhdsuhail.ratemyproperty.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "descriptions", primaryKeys = ["id"],
    foreignKeys =
    [ForeignKey(
        entity = PropertyDetails::class,
        parentColumns = arrayOf("uri"),
        childColumns = arrayOf("prop_uri"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class PropertyDescription(
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "prop_uri", index = true) val prop_uri: String,
    @ColumnInfo(name = "text") val text: String
) {
    companion object {
        const val lengthLimit = 500
    }
}