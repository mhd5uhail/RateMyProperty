package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.data.Contributor

class PreviewContributorProvider : PreviewParameterProvider<Contributor> {
    override val values = sequenceOf(
        Contributor("1","Mohammed Suhail","Realtor", R.drawable.sample_realtor,"523-349-233"),
        Contributor("2","Tony Stark","Iron Man", R.drawable.sample_realtor_2,"523-249-433"),
        Contributor("3","Son Goku ","Saiyan", R.drawable.sample_realtor_3,"523-149-933")
    )
}