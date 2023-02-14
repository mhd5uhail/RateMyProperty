package com.mhdsuhail.ratemyproperty.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(
    tableName = "addresses", foreignKeys = [
        ForeignKey(
            Property::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("propId"),
            onDelete = CASCADE
        )
    ]
)
data class Address(
    @PrimaryKey
    val id: Int? = null,
    val propId: Int,
    val country: String,
    val state: String,
    val city: String,
    val street: String,
    val unitNum: String,
    val postalCode: String
)