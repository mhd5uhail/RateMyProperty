package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Feature

class FeaturePreviewProvider : PreviewParameterProvider<Feature> {
    override val values = sequenceOf(
        Feature("Area",R.drawable.square_foot,"sqft","580","Carpet Area"),
        Feature("Bedrooms",R.drawable.bedroom,"bedrooms","4","Number of Bedrooms"),
        Feature("Bathrooms",R.drawable.shower,"bathrooms","3","Number of Bathrooms"),
        Feature("Car Parking",R.drawable.car_parking,"spot","2","Number of Parking Spots"),
        Feature("Swimming Pool",R.drawable.swimming_pool,"","Available","Pool available")
    )
}
