package com.mhdsuhail.ratemyproperty.data

data class Feature(
    val name:String,
    val imageResource: Int,
    val unit: String,
    val value: String,
    val desc: String?
)