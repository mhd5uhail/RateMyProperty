package com.mhdsuhail.ratemyproperty.ui.globalui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhdsuhail.ratemyproperty.MainActivity
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun PreviewOutlinedDropDown() {
    RateMyPropertyTheme {
        val expanded = remember {
            mutableStateOf(false)
        }
        val featureVal = remember {
            mutableStateOf("")
        }
        val dropDownItems = remember {
            listOf("Area", "Swimming Pool", "Parking", "Bedrooms", "Patio")
        }
        OutlinedDropDown(
            label = "Test",
            expanded = expanded,
            text = featureVal,
            dropDownList = dropDownItems,
            onSelectItem = {}
        )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedDropDownWFilter(
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean>,
    label: String?,
    dropDownList: List<String>?,
    text: MutableState<String>,
    onSelectItem: (String) -> Unit = {}// To control any other event that may happen
) {

    Box(modifier = modifier.wrapContentSize()) {
        ExposedDropdownMenuBox(expanded = expanded.value, onExpandedChange = {
            expanded.value = !expanded.value
        }) {
            OutlinedTextField(
                isError = dropDownList?.any{ text.value == it } == false ,
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                label = {
                    label?.let { Text(text = label) }
                },
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) }
            )
            dropDownList?.let {
                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = {
//                        expanded.value = false
                    }) {

                    val filteredList =
                        dropDownList.filter { it.contains(text.value, ignoreCase = true) }

                    filteredList.forEach {
                        DropdownMenuItem(onClick = {
                            text.value = it
                            expanded.value = false
                            onSelectItem(it)
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun OutlinedDropDown(
    modifier: Modifier = Modifier,
    expanded: MutableState<Boolean>,
    label: String?,
    dropDownList: List<String>?,
    text: MutableState<String>,
    onSelectItem: (String) -> Unit = {} // To control any other event that may happen
) {

    Box(modifier = modifier.wrapContentSize()) {
        ExposedDropdownMenuBox(expanded = expanded.value, onExpandedChange = {
            expanded.value = !expanded.value
        }) {
            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                label = {
                    label?.let { Text(text = label) }
                },
                value = text.value,
                onValueChange = {
                    text.value = it
                },
                singleLine = true,
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value) }
            )
            dropDownList?.let {
                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = {
                        expanded.value = false
                    }) {
                    dropDownList.forEach {
                        DropdownMenuItem(onClick = {
                            text.value = it
                            expanded.value = false
                            onSelectItem(it)
                        }) {
                            Text(text = it)
                        }
                    }
                }
            }

        }
    }
}