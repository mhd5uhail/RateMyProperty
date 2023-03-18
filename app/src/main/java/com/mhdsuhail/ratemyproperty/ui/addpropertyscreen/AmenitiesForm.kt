package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.json.AssetJsonParserImpl
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository
import com.mhdsuhail.ratemyproperty.ui.globalui.OutlinedDropDown
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.FeatureItem
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor

@Preview
@Composable
fun PreviewAmenityAddDialog() {
    RateMyPropertyTheme {
        AmenityAddDialog(onAddFeature = {}, onCancel = {}, unitsOfFeature = emptyMap())
    }
}

@Composable
fun AmenityAddDialog(
    modifier: Modifier = Modifier,
    unitsOfFeature: Map<String, List<String>>,
    onAddFeature: (feature: Feature) -> Unit,
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


    Surface(
        modifier = modifier
            .wrapContentSize()
            .clip(
                shape = RoundedCornerShape(10.dp)
            )
            .border(width = 2.dp, color = Blue200, shape = RoundedCornerShape(10.dp)),
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
                dropDownList = unitsOfFeature.keys.toList(),
                onSelectItem = {
                    unitText.value = ""
                }
            )
            OutlinedDropDown(
                text = unitText,
                expanded = expandedUnitMenu,
                label = "Select Unit",
                dropDownList = unitsOfFeature[featureText.value],
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
                        onAddFeature(
                            Feature(
                                prop_uri = "",
                                name = featureText.value,
                                unit = unitText.value,
                                value = valueText.value,
                                description = "",
                                imageResource = null
                            )
                        )
                    }
                    ) {
                        Text(text = stringResource(id = R.string.addFeature))
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun AmenitiesForm(
    modifier: Modifier = Modifier,
    viewModel: AddPropertyScreenViewModel
) {
    val lazyListState = rememberLazyListState()
    val scaffoldState = rememberScaffoldState()
    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    Scaffold(scaffoldState = scaffoldState, floatingActionButton = {

        if (dialogState.value) {
            Dialog(onDismissRequest = { dialogState.value = false }) {
                AmenityAddDialog(onAddFeature = { draftFeature ->
                    viewModel.onEvent(
                        AddPropertyScreenEvents.OnClickSubmitFeatureCreateDialog(feature = draftFeature)
                    )
                    dialogState.value = false
                }, onCancel = {
                    dialogState.value = false
                },
                    unitsOfFeature = viewModel.unitsOfFeature
                )
            }
        }

        IconButton(
            modifier = Modifier.background(shape = RoundedCornerShape(20.dp), color = Blue200),
            onClick = {
                dialogState.value = true
            }) {
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(15.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.alignByBaseline(),
                    text = "ADD",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Add feature"
                )
            }
        }
    }) { paddingValues ->
        Column(
            modifier = modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(text = stringResource(id = R.string.amenities))
            if (viewModel.featuresListState.isEmpty()) {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Add all the amenities that are available !",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = primaryTextColor,
                        fontSize = 15.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(top = 10.dp, start = 20.dp, bottom = 10.dp)
                            .align(Alignment.Center)
                    )
                }
            } else {

                LazyColumn(state = lazyListState, modifier = Modifier.fillMaxSize()) {
                    items(items = viewModel.featuresListState, key = { it.name }) { feature ->
                        val currentItem by rememberUpdatedState(newValue = feature)
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    Log.i("Deleting item", "AmenitiesForm: ${currentItem.name}")
                                    viewModel.onEvent(
                                        AddPropertyScreenEvents.FeatureDismissed(
                                            currentItem
                                        )
                                    )
                                    true
                                } else
                                    false
                            }
                        )
                        SwipeToDismiss(
                            modifier = Modifier
                                .wrapContentSize()
                                .animateItemPlacement(),
                            state = dismissState,
                            directions = setOf(DismissDirection.EndToStart),
                            background = {
                                SwipeBackGround(dismissState = dismissState)
                            },
                            dismissThresholds = { FractionalThreshold(0.5f) },
                            dismissContent = {
                                Surface {
                                    FeatureItem(feature = feature)
                                }
                            }
                        )
                    }
                }
            }
        }

    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SwipeBackGround(dismissState: DismissState) {
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
    )
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(horizontal = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = "Delete feature",
            modifier = Modifier.scale(scale)
        )
    }
}

@Preview
@Composable
fun PreviewAmenitiesForm() {
    RateMyPropertyTheme {
        AmenitiesForm(
            viewModel = AddPropertyScreenViewModel(
                propertyRepository = PreviewPropertyRepository(),
                application = Application(),
                assetRepository = PreviewAssetRepository()
            )
        )
    }
}