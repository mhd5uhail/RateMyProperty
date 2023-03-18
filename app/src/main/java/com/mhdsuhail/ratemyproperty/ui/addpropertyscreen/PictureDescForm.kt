package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.preview.PreviewPropertyRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewAssetRepository

@Preview
@Composable
fun PreviewPictureDescForm() {
    PictureDescForm(
        viewModel = AddPropertyScreenViewModel(
            assetRepository = PreviewAssetRepository(),
            propertyRepository = PreviewPropertyRepository(),
            application = Application(),
        )
    )
}

@Composable
fun PictureDescForm(modifier: Modifier = Modifier, viewModel: AddPropertyScreenViewModel) {

    val scaffoldState = rememberScaffoldState()
    val description = remember {
        viewModel.descriptionFormState.text
    }

    Scaffold(modifier = modifier, scaffoldState = scaffoldState) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.LightGray, shape = RoundedCornerShape(40.dp)),
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.add_photo),
                        contentDescription = "Add photo"
                    )
                }
            }
            Box(modifier = Modifier.weight(1f)) {
                OutlinedTextField(value = description.value, onValueChange ={
                    description.value = it
                } )

            }
        }
    }

}