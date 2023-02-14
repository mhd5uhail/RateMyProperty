package com.mhdsuhail.ratemyproperty.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

// TODO: Features image selection utility function/class needs to be created
@Entity(
    tableName = "features", foreignKeys =
    [ForeignKey(
        entity = Property::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("propId"),
        onDelete = CASCADE
    )]
)
data class Feature(
    @PrimaryKey
    val id: Int? = null,
    val propId: Int,
    val name: String,
    val imageResource: Int,
    val unit: String,
    val value: String,
    val desc: String?
)