package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*

data class PropertySampleData(
    val sample: Property = Property(
        propertyDetails = PropertyDetails(
            uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
            price = 1300,
            currency = "$",
            recentlyViewed = true,
            favourite = true,
            imagePropertyUri = null,
            address = Address("Canada", "ON", "Toronto", "88 Harbor St N", "1432", "H2A 4L2"),
            Contributor("1","test","test_title",null,"519213123")
        ),
        features = listOf(
            Feature(1,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Area",R.drawable.square_foot,"sqft","580","Carpet Area"),
            Feature(2,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Bedrooms",R.drawable.bedroom,"bedrooms","4","Number of Bedrooms"),
            Feature(3,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Bathrooms",R.drawable.shower,"bathrooms","3","Number of Bathrooms"),
            Feature(4,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Car Parking",R.drawable.car_parking,"spot","2","Number of Parking Spots"),
            Feature(5,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Swimming Pool",R.drawable.swimming_pool,"","Available","Pool available")
        ),
        description = PropertyDescription(prop_uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec quis finibus sem. Duis nec dolor et tortor malesuada pellentesque. Suspendisse porttitor tempus lectus, non commodo orci rhoncus et. Praesent odio est, ultricies sed augue ut, laoreet congue magna. Duis semper suscipit bibendum. Maecenas semper dolor vel nulla congue dignissim. Ut pretium lobortis felis a tristique\n")
    )
)