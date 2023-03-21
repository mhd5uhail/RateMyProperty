package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.PropertyDescription
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository
import com.mhdsuhail.ratemyproperty.ui.globalui.TitleText

@Preview
@Composable
fun PreviewPictureDescForm() {
    PictureDescForm(
        viewModel = AddPropertyScreenViewModel(
            assetRepository = PreviewAssetRepository(),
            propertyRepository = PreviewPropertyRepository(),
        )
    )
}

@Composable
fun PictureDescForm(modifier: Modifier = Modifier, viewModel: AddPropertyScreenViewModel ) {

    val scaffoldState = rememberScaffoldState()
    val description = remember {
        viewModel.descriptionFormState.text
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            uri?.let {
                viewModel.onEvent(AddPropertyScreenEvents.ClickAddNewImage(uri))
            }
        }
    )

    Scaffold(modifier = modifier, scaffoldState = scaffoldState) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(text = stringResource(id = R.string.description))

            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val imageShape = RoundedCornerShape(20.dp)
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .background(
                            color = Color.LightGray,
                            shape = imageShape
                        )
                ) {

                    if (viewModel.selectedImageUri.value != null) {
                        AsyncImage(
                            model = viewModel.selectedImageUri.value,
                            contentDescription = "Property Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(shape = imageShape)
                        )
                        IconButton(
                            modifier = Modifier
                                .size(60.dp)
                                .align(Alignment.TopEnd),
                            onClick = {
                                // Get a picture from gallery or from camera
                                viewModel.selectedImageUri.value?.let {
                                    viewModel.onEvent(AddPropertyScreenEvents.ClickRemoveImage(it))
                                }

                            },
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center),
                                painter = painterResource(id = R.drawable.remove_circle),
                                contentDescription = "Remove photo"
                            )
                        }
                    } else {

                        IconButton(
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.Center),
                            onClick = {
                                // Get a picture from gallery or from camera
                                singlePhotoPickerLauncher.launch(
                                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                )
                            },
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(40.dp)
                                    .align(Alignment.Center),
                                painter = painterResource(id = R.drawable.add_photo),
                                contentDescription = "Add photo"
                            )
                        }

                    }
                }
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxSize()
                        .padding(top = 20.dp)
                ) {
                    OutlinedTextField(
                        label = {
                            Text(text = stringResource(id = R.string.description_prompt) + " (${description.value.length}/${PropertyDescription.lengthLimit})" )
                        },
                        modifier = Modifier.fillMaxSize(),
                        value = description.value,
                        onValueChange = {
                            if(it.length <= PropertyDescription.lengthLimit)
                                description.value = it
                        })

                }
            }
        }
    }
}