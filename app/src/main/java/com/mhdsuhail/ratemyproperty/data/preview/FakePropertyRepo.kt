package com.mhdsuhail.ratemyproperty.data.preview

import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.*

class FakePropertyWInfoRepo() : PropertyWExtraInfoRepo {

    override fun getPropertyWithExtraInfoById(uri: String): PropertyWithExtraInfo? {
        return PropertyWithExtraInfo(
            property = Property(
                uri = "90741389-caa6-4d22-9f4f-1a4201db3be1",
                price = 1300,
                currency = "$",
                recentlyViewed = true,
                favourite = true,
                imageResourceId = null,
                address = Address("Canada", "ON", "Toronto", "88 Harbor St N", "1432", "H2A 4L2"),
                posterContact = PosterContact(
                    "Mohammed Suhail",
                    "Realtor",
                    R.drawable.sample_realtor,
                    "523-349-233"
                ),
            ),
            features = FeaturePreviewProvider().values.toList(),
            description = null
        )
    }
}