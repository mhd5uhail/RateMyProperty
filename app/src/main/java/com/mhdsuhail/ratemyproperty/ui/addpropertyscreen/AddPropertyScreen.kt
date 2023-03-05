package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.mhdsuhail.ratemyproperty.data.MileStone
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme


@Preview
@Composable
fun PreviewAddPropertyScreen() {
    RateMyPropertyTheme {
        Surface {
            AddPropertyScreen()
        }
    }
}

@Composable
fun ProgressBarWithMileStones(
    modifier: Modifier = Modifier,
    mileStones: List<MileStone>,
    onCheckedChange: (mileStone: MileStone) -> Unit
) {
    Box(modifier = modifier) {
        
        Row(
            modifier = Modifier.align(Alignment.Center)
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            mileStones.forEach { mileStone ->
                MileStone(
                    Modifier.weight(1f),
                    taskCheckBox = mileStone,
                    onCheckedChange = { onCheckedChange(mileStone) })
            }
        }

    }
}

@Composable
fun MileStone(modifier: Modifier = Modifier, taskCheckBox: MileStone, onCheckedChange: (value: Boolean) -> Unit) {
    Column(
        modifier = modifier
            .wrapContentHeight()
            .wrapContentWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Checkbox(checked = taskCheckBox.isDone,
            onCheckedChange = { onCheckedChange(it) })
        Text(text = taskCheckBox.name)
    }
}

@Composable
fun AddPropertyScreen() {
    Scaffold(topBar = {

        ProgressBarWithMileStones(
            mileStones = listOf(
                MileStone("Details", false),
                MileStone("Features", false),
                MileStone("Picture", false),
                MileStone("Review", false)
            ),
            onCheckedChange = {}
        )

    }) { paddingValues ->

        Column(modifier = Modifier.padding(paddingValues)) {


        }
    }
}