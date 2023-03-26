package com.mhdsuhail.ratemyproperty.data

import androidx.room.ColumnInfo

data class Contributor(
    @ColumnInfo(name = "contributor_id") val id: String,
    @ColumnInfo(name = "contributor_name") val name: String,
    @ColumnInfo(name = "contributor_title") val title: String,
    @ColumnInfo(name = "contributor_image") val imageContributorUri: Int?,
    @ColumnInfo(name = "contributor_phone") val phoneNumber: String?,
)