package com.mhdsuhail.ratemyproperty.data

import androidx.compose.runtime.MutableState

data class TaskCheckBox(val name: String, val isChecked: MutableState<Boolean>)