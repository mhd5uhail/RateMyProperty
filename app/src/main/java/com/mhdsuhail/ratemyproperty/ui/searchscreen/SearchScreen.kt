package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import com.mhdsuhail.ratemyproperty.ui.theme.primaryTextColor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.preview.NavItemsPreviewProvider
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.navbar.BottomNavBar
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun SearchScreenPreview() {
    RateMyPropertyTheme() {
        SearchScreen(PropertyPreviewParameterProvider().values.toList())
    }
}


//@Preview
//@Composable
//fun TopActionBarPreview() {
//    RateMyPropertyTheme {
//        TopActionBar()
//    }
//}

@Composable
fun TopActionBar() {

    Surface {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(45.dp)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape), // Descriptive Image
                    painter = painterResource(id = R.drawable.sample_realtor_2),
                    contentScale = ContentScale.Crop,
                    contentDescription = "Realtor Contact Picture"
                )
            }
        }
    }
}

@Composable
fun SearchScreen(recentlyViewedProperties: List<Property>) {
    val scaffoldState = rememberScaffoldState()
    val navItems = NavItemsPreviewProvider().values.elementAt(0)


    Scaffold(scaffoldState = scaffoldState,
        topBar = { TopActionBar() },
        bottomBar = { BottomNavBar(navItems) }) {

        Column(
            modifier = Modifier
                .fillMaxSize(), verticalArrangement = Arrangement.Center,
        ) {
            WelcomeBanner(modifier = Modifier.height(100.dp))
            SearchBar()
            /** TODO: When a property is searched for (type something and press search
             * PropertyList gets populated with the search results and the
             * recent searches Text changes to a search filter with several options
             **/
            Text(
                text = "Recent searches",
                textAlign = TextAlign.Start,
                fontWeight = FontWeight.Bold,
                color = primaryTextColor,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PropertyList(listOfProperties = recentlyViewedProperties)
        }

    }

}