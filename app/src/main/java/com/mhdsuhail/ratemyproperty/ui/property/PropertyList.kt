package com.mhdsuhail.ratemyproperty.ui.searchscreen
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.ui.propertycard.PropertyInfoCard

@Composable
fun PropertyList(listOfProperties: List<Property>){
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 15.dp, end = 15.dp)) {
        Column(Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(listOfProperties){ property->
                    PropertyInfoCard(property)
                }
            }
        }
    }
}
