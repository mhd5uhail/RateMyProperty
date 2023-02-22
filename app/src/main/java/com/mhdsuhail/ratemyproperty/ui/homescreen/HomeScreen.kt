package com.mhdsuhail.ratemyproperty.ui.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.property.PropertyInfoCardX
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import com.mhdsuhail.ratemyproperty.R
import java.time.LocalDateTime

@Preview
@Composable
fun HomeScreenPreview() {
    RateMyPropertyTheme {
        Surface() {
            HomeScreen()
        }
    }
}

@Composable
fun TitledLazyRowProperty(
    modifier: Modifier = Modifier,
    title: String,
    properties: List<Property>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {

        Text(
            text = title,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = 25.sp,
            modifier = Modifier.padding(start = 20.dp)
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {

            items(properties) { property ->
                PropertyInfoCardX(
                    modifier = Modifier.padding(10.dp),
                    property = property,
                    onClickItem = {
                        // TODO:
                    },
                    onClickActionButton = {
                        // TODO:
                    })
            }

        }

    }
}

@Composable
fun NewsFeed(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier, verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "News",
            fontWeight = FontWeight.Bold,
            color = primaryTextColor,
            fontSize = 25.sp,
            modifier = Modifier.fillMaxWidth()
        )
        ArticleCard(modifier = Modifier.padding(top = 10.dp), summary = "", title = "", date = LocalDateTime.now())
    }
}

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    val properties = PropertyPreviewParameterProvider().values.toList()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        NewsFeed(
            modifier = Modifier.padding(start = 15.dp, end = 15.dp),)

        TitledLazyRowProperty(title = "Your Favorites", properties = properties)

        TitledLazyRowProperty(title = "Your Listings", properties = properties)

    }
}