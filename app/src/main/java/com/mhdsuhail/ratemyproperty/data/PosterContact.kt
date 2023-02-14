package com.mhdsuhail.ratemyproperty.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE

@Entity(
    tableName = "contacts", foreignKeys = [
        ForeignKey(
            Property::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("propId"),
            onDelete = CASCADE
        )
    ]
)
data class PosterContact(
    val id: Int? = null,
    val propId: Int,
    val name: String,
    val title: String,
    val imageResourceId: Int,
    val phoneNumber: String,
)