package com.mhdsuhail.ratemyproperty.ui.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.theme.Blue100
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import java.time.LocalDateTime
import java.util.Date

@Preview
@Composable
fun PreviewArticleCard(){
    RateMyPropertyTheme() {
        Surface {
            ArticleCard(summary = "", title = "", date = LocalDateTime.now())
        }
    }
}

@Composable
fun ArticleCard(modifier : Modifier = Modifier,imageResourceId : Int? = null, summary: String, title: String, date : LocalDateTime){
    Card(shape = RoundedCornerShape(15.dp), modifier = modifier
        .fillMaxWidth()
        .height(350.dp)
        .padding(bottom = 15.dp)
        .clickable(onClick = {
            // TODO:
        })
    ) {
        Surface {
            Column(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .background(
                            Brush.linearGradient(
                                listOf(
                                    Color.Cyan,
                                    Blue200, Blue100
                                )
                            )
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.rmplogo_white),
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit)
                }

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 5.dp)
                        .fillMaxWidth(),
                    text = "About us",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = primaryTextColor
                )

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 5.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.loremIpsum),
                    maxLines = 5,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Justify,
                    color = Color.Gray,
                    fontSize = 12.sp
                )

                Text(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 5.dp)
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.sample_date),
                    textAlign = TextAlign.Right,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    maxLines = 1
                )
            }

        }
    }
}