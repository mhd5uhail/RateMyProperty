package com.mhdsuhail.ratemyproperty.ui.favouritescreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import com.mhdsuhail.ratemyproperty.util.UiEvent


@Preview
@Composable
fun PreviewContributeScreen() {
    RateMyPropertyTheme() {
        Surface {
            ContributeScreen(onNavigate = {})
        }
    }
}

@Composable
fun ContributeScreen(onNavigate: (UiEvent.Navigate) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            text = stringResource(id = R.string.build_together),
            textAlign = TextAlign.Center,
            fontSize = 25.sp,
            color = primaryTextColor,
            fontWeight = FontWeight.SemiBold
        )

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            painter = painterResource(id = R.drawable.contribute),
            contentDescription = "DALLE-2 image",
            contentScale = ContentScale.Fit
        )

        Text(
            modifier = Modifier.padding(start = 30.dp, end = 30.dp, bottom = 20.dp, top = 20.dp),
            text = stringResource(id = R.string.help_us),
            textAlign = TextAlign.Center,
            color = Color.Gray,
            fontWeight = FontWeight.SemiBold
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(), onClick = { /*TODO*/ },
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Blue200), text = stringResource(id = R.string.add_property),
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            OutlinedButton(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
                onClick = { /*TODO*/ },
                border = BorderStroke(2.dp, Blue200),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    text = stringResource(id = R.string.report_mistake),
                    color = Blue200,
                    textAlign = TextAlign.Center
                )
            }
        }


    }
}