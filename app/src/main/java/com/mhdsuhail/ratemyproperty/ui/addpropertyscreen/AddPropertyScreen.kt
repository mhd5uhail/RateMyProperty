package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.data.TaskCheckBox
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun PreviewAddPropertyScreen() {
    RateMyPropertyTheme {
        Surface {
            //AddPropertyScreen()
            val state = remember {
                mutableStateOf(true)
            }
            ProgressBarCheckBox(
                taskCheckBox = TaskCheckBox("Address", state),
                onCheckedChange = { })  //todo: move to viewmodel state.value = !state.value })
        }
    }
}

@Composable
fun ProgressBar() {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
    }
}

@Composable
fun ProgressBarCheckBox(taskCheckBox: TaskCheckBox, onCheckedChange: (value: Boolean) -> Unit) {
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Checkbox(checked = taskCheckBox.isChecked.value,
            onCheckedChange = { onCheckedChange(it) })
        Text(text = taskCheckBox.name)
    }
}

@Composable
fun AddPropertyScreen() {
    Scaffold(topBar = { ProgressBar() }) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {


        }
    }
}