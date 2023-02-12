package com.mhdsuhail.ratemyproperty.ui.navbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mhdsuhail.ratemyproperty.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.data.NavBarItem
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme

@Preview
@Composable
fun BottomNavBarPreview() {
    RateMyPropertyTheme {
        Surface() {
            BottomNavBar()
        }
    }
}


@Composable
fun BottomNavBar() {

    // TODO: Parameterize BottomNavBar and move NavBarItems to AppModule and
    //  make it a singleton instance

    val listOfNavItems = listOf(
        NavBarItem(R.drawable.search,"Search","Search Page"),
        NavBarItem(R.drawable.favourite,"Favourites","Favourites List"),
        NavBarItem(R.drawable.chat,"Messages","Messages")
    )
    Surface(modifier = Modifier.alpha(0.9f)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp), verticalAlignment = Alignment.CenterVertically
        ) {

            listOfNavItems.forEach { navBarItem ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                ) {
                    NavBarOption(
                        imageResourceId = navBarItem.imageResourceId,
                        name = navBarItem.name,
                        desc = navBarItem.description
                    )
                }
            }
        }
    }
}

@Composable
fun NavBarOption(imageResourceId: Int, name: String, desc: String? ){

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
                    .background(Color.White.copy(alpha = 0.15f))
            ) {
                Icon(
                    painter = painterResource(id = imageResourceId),
                    modifier = Modifier.size(35.dp),
                    contentDescription = desc,
                    tint = Color.Gray
                )

            }
            Text(
                text = name,
                color = Color.Gray,
                fontSize = 14.sp
            )

        }
}