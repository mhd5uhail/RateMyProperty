package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.PosterContact

class PosterContactPreviewProvider : PreviewParameterProvider<PosterContact> {
    override val values = sequenceOf(
        PosterContact("Mohammed Suhail","Realtor", R.drawable.sample_realtor,"523-349-233"),
        PosterContact("Tony Stark","Iron Man", R.drawable.sample_realtor_2,"523-249-433"),
        PosterContact("Son Goku ","Saiyan", R.drawable.sample_realtor_3,"523-149-933")
    )
}