package com.mhdsuhail.ratemyproperty.ui.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.ui.theme.textFieldBackGround


@Preview
@Composable
fun SearchBarPreview() {

    RateMyPropertyTheme {
        SearchBar()
    }

}

@Composable
fun SearchBar(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {

        OutlinedTextField(value = "Where would you like to stay ?",
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = textFieldBackGround
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp),
            onValueChange = {}, trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }, shape = RoundedCornerShape(12.dp)
        )

        Button(
            onClick = { /*TODO*/ },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .height(60.dp)
        ) {
            Text(text = "Show Results", fontSize = 15.sp)
        }
    }
}