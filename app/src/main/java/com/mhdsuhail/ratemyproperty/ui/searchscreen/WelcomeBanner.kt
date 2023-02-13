package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.mhdsuhail.ratemyproperty.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor


@Preview
@Composable
fun WelcomeBannerPreview() {
    RateMyPropertyTheme() {
        androidx.compose.material.Surface {
            WelcomeBanner()
        }
    }
}

@Composable
fun WelcomeBanner(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Image(
            painter = painterResource(id = R.drawable.rmplogo),
            contentDescription = "RateMyPropertyLogo",
            contentScale = ContentScale.Fit,
            modifier = modifier.fillMaxSize()
        )


    }
}