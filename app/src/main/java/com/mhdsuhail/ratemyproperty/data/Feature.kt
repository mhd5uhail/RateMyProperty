package com.mhdsuhail.ratemyproperty.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

// TODO: Features image selection utility function/class needs to be created
@Entity(
    primaryKeys = ["id"],
    tableName = "features",
    foreignKeys =
    [ForeignKey(
        entity = PropertyDetails::class,
        parentColumns = arrayOf("uri"),
        childColumns = arrayOf("prop_uri"),
        onDelete = CASCADE
    )],
)
data class Feature(
    @ColumnInfo(name = "id") val id: Int? = null,
    @ColumnInfo(name = "prop_uri", index = true) val prop_uri: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "imageId") val imageResource: Int?,
    @ColumnInfo(name = "unit") val unit: String, // Unit of measurement
    @ColumnInfo(name = "value") val value: String,
    @ColumnInfo(name = "description") val description: String?
)