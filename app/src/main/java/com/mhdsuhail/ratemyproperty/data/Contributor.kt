package com.mhdsuhail.ratemyproperty.data

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.TypeConverters

@TypeConverters(UriTypeConverter::class)
data class Contributor(
    @ColumnInfo(name = "contributor_id") val id: String,
    @ColumnInfo(name = "contributor_name") val name: String,
    @ColumnInfo(name = "contributor_title") val title: String,
    @ColumnInfo(name = "contributor_image") val imageContributorUri: Uri?,
    @ColumnInfo(name = "contributor_phone") val phoneNumber: String?,
)