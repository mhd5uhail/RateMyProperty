package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Feature

class FeaturePreviewProvider : PreviewParameterProvider<Feature> {
    override val values = sequenceOf(
        Feature(1,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Area",R.drawable.square_foot,"sqft","580","Carpet Area"),
        Feature(2,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Bedrooms",R.drawable.bedroom,"bedrooms","4","Number of Bedrooms"),
        Feature(3,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Bathrooms",R.drawable.shower,"bathrooms","3","Number of Bathrooms"),
        Feature(4,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Car Parking",R.drawable.car_parking,"spot","2","Number of Parking Spots"),
        Feature(5,"59ac0c32-cc0e-49f9-a881-c0bd073f11cd","Swimming Pool",R.drawable.swimming_pool,"","Available","Pool available")
    )
}
