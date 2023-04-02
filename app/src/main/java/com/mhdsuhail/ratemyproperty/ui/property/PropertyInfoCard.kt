package com.mhdsuhail.ratemyproperty.ui.property

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider

@Preview
@Composable
fun PropertyInfoCardXPreview(
    @PreviewParameter(PropertyPreviewParameterProvider::class) properties: PropertyDetails
) {
    PropertyInfoCard(propertyDetails = properties, onClickItem = {}, onClickActionButton = {})
}

@Composable
fun PropertyInfoCard(
    modifier: Modifier = Modifier,
    propertyDetails: PropertyDetails,
    onClickItem: (propertyDetails: PropertyDetails) -> Unit,
    onClickActionButton: (propertyDetails: PropertyDetails) -> Unit,
) {

    Card(
        shape = RoundedCornerShape(20.dp), modifier = modifier
            .width(280.dp)
            .height(300.dp)
            .padding(bottom = 15.dp)
            .clickable(onClick = {
                onClickItem(propertyDetails)
            })
    ) {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.65F)
                        .fillMaxWidth()
                ) {
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = propertyDetails.imagePropertyUri,
                        contentDescription = "PropertyImage",
                        contentScale = ContentScale.FillBounds
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            start = 15.dp,
                            end = 15.dp,
                            top = 1.dp
                        )
                        .fillMaxSize(0.5F)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = "${propertyDetails.address.street} \n ${propertyDetails.address.city}," + " ${propertyDetails.address.province}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = primaryTextColor
                        )
                        IconButton(modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp)
                            .align(Alignment.BottomEnd),
                            onClick = { onClickActionButton(propertyDetails) }) {
                            Icon(
                                imageVector = if (propertyDetails.favourite) {
                                    Icons.Rounded.Favorite
                                } else {
                                    Icons.Rounded.FavoriteBorder
                                },
                                contentDescription = "Favourites",
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }

}