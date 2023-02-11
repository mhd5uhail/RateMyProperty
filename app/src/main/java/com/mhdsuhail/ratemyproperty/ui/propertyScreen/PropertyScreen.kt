package com.mhdsuhail.ratemyproperty.ui.propertyScreen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Cyan
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Feature
import com.mhdsuhail.ratemyproperty.data.PosterContact
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.preview.FeaturePreviewProvider
import com.mhdsuhail.ratemyproperty.data.preview.PosterContactPreviewProvider
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import com.mhdsuhail.ratemyproperty.ui.theme.Purple200
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor

@Preview
@Composable
fun PropertyScreenPreview(
    @PreviewParameter(PropertyPreviewParameterProvider::class) property: Property
) {
    RateMyPropertyTheme() {
        PropertyScreen(property)
    }
}

@Composable
fun PropertyScreen(property: Property) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = { ContactCard(contactInfo = property.posterContact) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp)
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.propertyprop2),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(40.dp)),
                        contentScale = ContentScale.FillBounds
                    )

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .offset(x = (-10).dp, y = 10.dp)
                            .clip(CircleShape)
                            .size(60.dp)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.FavoriteBorder,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "Add to Favourites",
                            tint = Color.White
                        )
                    }

                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(x = (10).dp, y = 10.dp)
                            .clip(CircleShape)
                            .size(60.dp)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            modifier = Modifier.size(30.dp),
                            contentDescription = "Go back to previous page",
                            tint = Color.White
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top = 1.dp)
                            .fillMaxWidth(),
                        text = property.currency + property.price.toString(),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = primaryTextColor
                    )
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 1.dp)
                                .fillMaxWidth(0.85F),
                            text = "${property.address.street} - ${property.address.city}," + " ${property.address.state}",
                            fontSize = 18.sp,
                            color = primaryTextColor
                        )
                    }

                    Divider(
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                        color = Color.LightGray, thickness = 2.dp
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                    ) {

                        Column(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = stringResource(id = R.string.loremIpsum),
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis,
                                textAlign = TextAlign.Left,
                                color = Color.Gray
                            )

                            ClickableText(modifier = Modifier.align(Alignment.End),
                                text = AnnotatedString(
                                    text = "Show more",
                                    spanStyle = SpanStyle(
                                        color = primaryTextColor,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 17.sp
                                    )
                                ),
                                onClick = {/*TODO*/ })
                        }

                    }

                    Divider(
                        modifier = Modifier.padding(top = 15.dp, bottom = 15.dp),
                        color = Color.LightGray, thickness = 2.dp
                    )

                    FeaturesList(property.features)
                    // To compensate for bottom app bar
                    Spacer(modifier = Modifier.height(85.dp))
                }
            }
        }
    }
}

//@Preview
//@Composable
//fun ContactCardPreview( @PreviewParameter(PosterContactPreviewProvider::class) contactInfo : PosterContact){
//    RateMyPropertyTheme() {
//        Surface {
//            ContactCard(contactInfo)
//        }
//    }
//}

@Composable
fun ContactCard(contactInfo: PosterContact) {
    Card(shape = RoundedCornerShape(35.dp), modifier = Modifier
        .fillMaxWidth()
        .height(85.dp),
        border = BorderStroke(width = 1.5.dp, color = LightGray),
            backgroundColor = Color.White) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(64.dp)
                        .clip(CircleShape), // Descriptive Image
                    painter = painterResource(id = contactInfo.imageResourceId),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Realtor Contact Picture"
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 15.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(modifier = Modifier.width(140.dp), text =
                    buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.Gray)) {
                            append("${contactInfo.title}\n")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = primaryTextColor,
                                fontWeight = FontWeight.SemiBold
                            )
                        ) {
                            append(contactInfo.name)
                        }
                    }
                    )
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clip(CircleShape)
                        .size(55.dp)
                        .background(Color.Blue.copy(alpha = 0.15f))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.chat),
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Chat with Poster",
                        tint = Color.Blue
                    )
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clip(CircleShape)
                        .size(55.dp)
                        .background(Color.Green.copy(alpha = 0.15f))
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Phone,
                        modifier = Modifier.size(30.dp),
                        contentDescription = "Call Poster",
                        tint = Color.Green
                    )
                }
            }
        }
}


@Composable
fun FeaturesList(features: List<Feature>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .padding(top = 1.dp)
                .fillMaxWidth(),
            text = stringResource(id = R.string.feature_text),
            fontSize = 25.sp,
            fontWeight = FontWeight.SemiBold,
            color = primaryTextColor
        )
        features.forEach { feature ->
            FeatureItem(feature)
        }
    }
}

//@Preview
//@Composable
//fun FeatureItemPreview(@PreviewParameter(FeaturePreviewProvider::class) feature: Feature) {
//    RateMyPropertyTheme() {
//        Surface() {
//            FeatureItem(feature)
//        }
//    }
//}

@Composable
fun FeatureItem(feature: Feature) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .fillMaxHeight()
                .width(30.dp), // Descriptive Image
            painter = painterResource(id = feature.imageResource),
            contentDescription = feature.desc
        )

        Text(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(10.dp),
            text = feature.name, // Type
            color = Color.Gray
        )
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(10.dp),
                text = "${feature.value} ${feature.unit}", // Value + unit
                fontWeight = FontWeight.SemiBold,
                color = primaryTextColor
            )
        }

    }
}