package com.mhdsuhail.ratemyproperty.ui.propertycard

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun PropertyInfoCardPreview(
    @PreviewParameter(PropertyPreviewParameterProvider::class) properties: Property
) {
    PropertyInfoCard(property =  properties, onClickItem = {})
}

@Composable
fun PropertyInfoCard( modifier: Modifier = Modifier,property: Property, onClickItem : (prop_uri: String)->Unit) {

    RateMyPropertyTheme() {

        Card(shape = RoundedCornerShape(40.dp), modifier = Modifier
            .width(330.dp)
            .height(350.dp)
            .padding(bottom = 15.dp).clickable(onClick = {
                    onClickItem(property.uri)
            })) {
            Surface {
                Column(modifier = modifier.fillMaxSize()) {
                    Box(modifier = modifier
                        .fillMaxHeight(0.65F)
                        .fillMaxWidth()){
                        Image(
                            painter = painterResource(id = R.drawable.propertyprop2),
                            contentDescription = "",
                            modifier = modifier.fillMaxSize(),
                            contentScale = ContentScale.FillBounds
                        )
                    }
                    Column(
                        modifier = modifier
                            .fillMaxSize()
                            .padding(
                                start = 15.dp,
                                end = 15.dp,
                                top = 1.dp
                            )
                            .fillMaxSize(0.5F)
                    ) {
                        Text(
                            modifier = modifier
                                .padding(top = 1.dp)
                                .fillMaxWidth(),
                            text = "${property.currency}" + property.price.toString(),
                            fontSize = 35.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryTextColor
                        )
                        Row(
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.Top,
                            modifier = modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = modifier
                                    .padding(top = 1.dp)
                                    .fillMaxWidth(0.85F),
                                text = "${property.address.street} - ${property.address.city}," + " ${property.address.state}",
                                fontSize = 18.sp,
                                color = primaryTextColor
                            )
                            IconButton( modifier = modifier.fillMaxSize(),
                                onClick = { /*TODO*/ }) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Favourites",
                                    modifier = modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}