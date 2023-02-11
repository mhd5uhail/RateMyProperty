package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.data.Property
import com.mhdsuhail.ratemyproperty.data.Address

class PropertyPreviewParameterProvider : PreviewParameterProvider<Property> {
    val adPosters = PosterContactPreviewProvider().values
    override val values = sequenceOf(
        Property("1",
            Address("Canada","ON","Waterloo","350 Columbia St W","102","N2L6P1"),
            854,"$",null,FeaturePreviewProvider().values.toList(),adPosters.elementAt(0)),
        Property("2",
            Address("Canada","ON","Toronto","88 Harbor St N","1432","H2A 4L2"),
            2500,"$",null,FeaturePreviewProvider().values.toList(),adPosters.elementAt(1)),
        Property("3",
            Address("Canada","ON","Ottawa","69 Fischer-Hallman St E","350","G91 4AK"),
            1200,"$",null, FeaturePreviewProvider().values.toList(),adPosters.elementAt(2)),
    )
}