package com.mhdsuhail.ratemyproperty.data.preview

import android.net.Uri
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.BuildConfig
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.PropertyDetails
import com.mhdsuhail.ratemyproperty.data.Address

class PropertyPreviewParameterProvider : PreviewParameterProvider<PropertyDetails> {
    private val contributors = PreviewContributorProvider().values
    private val androidResourceUri :String = "android.resource://com.mhdsuhail.ratemyproperty"
    override val values = sequenceOf(
        PropertyDetails(
            uri = "59ac0c32-cc0e-49f9-a881-c0bd073f11cd",
            price = 2500,
            currency = "$",
            recentlyViewed = true,
            favourite = true,
            imagePropertyUri = Uri.parse("$androidResourceUri/${R.drawable.propertyprop}"),
            address = Address("Canada", "ON", "Waterloo", "350 Columbia St W", "102", "N2L6P1"),
            contributor = contributors.elementAt(0)
        ),

        PropertyDetails(
            uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",
            price = 1300,
            currency = "$",
            recentlyViewed = true,
            favourite = false,
            imagePropertyUri = Uri.parse("$androidResourceUri/${R.drawable.propertyprop2}"),
            address = Address("Canada", "ON", "Toronto", "88 Harbor St N", "1432", "H2A 4L2"),
            contributor = contributors.elementAt(1)
        ),

        PropertyDetails(
            uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",
            price = 849,
            currency = "$",
            recentlyViewed = true,
            favourite = false,
            imagePropertyUri = Uri.parse("$androidResourceUri/${R.drawable.propertyprop2}"),
            address = Address(
                "Canada",
                "ON",
                "Ottawa",
                "69 Fischer-Hallman St E",
                "350",
                "G91 4AK"
            ),
            contributor = contributors.elementAt(2)
        ),

        )
}