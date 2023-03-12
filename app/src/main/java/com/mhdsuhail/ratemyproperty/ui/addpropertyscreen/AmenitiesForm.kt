package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.globalui.OutlinedDropDown
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun PreviewAmenityAddDialog() {
    RateMyPropertyTheme {
        AmenityAddDialog(onAddFeature = {}, onCancel = {})
    }
}

@Composable
fun AmenityAddDialog(
    modifier: Modifier = Modifier,
    onAddFeature: () -> Unit,
    onCancel: () -> Unit
) {
    val expandedFeatureMenu = remember {
        mutableStateOf(false)
    }
    val expandedUnitMenu = remember {
        mutableStateOf(false)
    }
    val featureText = remember {
        mutableStateOf("")
    }
    val unitText = remember {
        mutableStateOf("")
    }
    val valueText = remember {
        mutableStateOf("")
    }

    val listOfFeatures = listOf("Area", "Swimming Pool", "Parking", "Bedrooms", "Patio")
    val listOfUnits = listOf("Present/Absent", "Sqft", "m2", "Count")

    Surface(
        modifier = modifier
            .wrapContentSize()
            .clip(
                shape = RoundedCornerShape(20.dp)
            )
            .border(width = 2.dp, color = Blue200, shape = RoundedCornerShape(20.dp)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(20.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            OutlinedDropDown(
                text = featureText,
                expanded = expandedFeatureMenu,
                label = "Select Feature",
                dropDownList = listOfFeatures,
            )
            OutlinedDropDown(
                text = unitText,
                expanded = expandedUnitMenu,
                label = "Select Unit",
                dropDownList = listOfUnits
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                label = { Text(text = "Value") },
                value = valueText.value,
                onValueChange = {
                    valueText.value = it
                },
                singleLine = true,
                trailingIcon = { Icons.Default.Settings }
            )

            Row(
                modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(5.dp)
                            .align(Alignment.Center), onClick = {
                            onCancel()
                        },
                        border = BorderStroke(width = 1.dp, color = Blue200)
                    ) {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    Button(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(5.dp)
                        .align(Alignment.Center), onClick = {
                        onAddFeature()
                    }
                    ) {
                        Text(text = stringResource(id = R.string.addFeature))
                    }
                }
            }

        }
    }
}

@Composable
fun AmenitiesForm(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    Scaffold(scaffoldState = scaffoldState, floatingActionButton = {

        if (dialogState.value) {
            Dialog(onDismissRequest = { dialogState.value = false }) {
                AmenityAddDialog(onAddFeature = {
                    // TODO:
                    dialogState.value = false
                }, onCancel = {
                    dialogState.value = false
                })
            }
        }

        IconButton(
            modifier = Modifier.background(shape = CircleShape, color = Blue200),
            onClick = {
                dialogState.value = true
            }) {
            Icon(
                imageVector = Icons.Default.Add,
                tint = Color.White,
                contentDescription = "Add feature"
            )

        }
    }) { paddingValues ->

        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            TitleText(text = stringResource(id = R.string.amenities))


        }

    }

}

@Preview
@Composable
fun PreviewAmenitiesForm() {
    RateMyPropertyTheme {
        AmenitiesForm()

    }
}