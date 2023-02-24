package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.data.PropertyDetails

class PropertyListProvider : PreviewParameterProvider<List<PropertyDetails>> {
    override val values = sequenceOf(
        PropertyPreviewParameterProvider().values.toList()
    )
}