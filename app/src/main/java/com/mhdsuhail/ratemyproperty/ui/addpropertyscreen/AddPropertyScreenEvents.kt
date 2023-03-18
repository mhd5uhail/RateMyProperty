package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import android.net.Uri
import com.mhdsuhail.ratemyproperty.data.Feature

sealed class AddPropertyScreenEvents{
    data class OnClickSubmitPage(val currentPageRoute : String) : AddPropertyScreenEvents()
    object  OnClickSubmitForm : AddPropertyScreenEvents()
    object OnBackPressed: AddPropertyScreenEvents()
    data class OnClickSubmitFeatureCreateDialog(val feature: Feature) : AddPropertyScreenEvents()
    data class FeatureDismissed(val feature: Feature) : AddPropertyScreenEvents()

    data class ClickAddNewImage(val uri: Uri) : AddPropertyScreenEvents()
}