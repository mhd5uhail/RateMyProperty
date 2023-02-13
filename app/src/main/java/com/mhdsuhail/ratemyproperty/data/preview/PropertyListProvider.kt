package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.data.Property

class PropertyListProvider : PreviewParameterProvider<List<Property>> {
    override val values = sequenceOf(
        PropertyPreviewParameterProvider().values.toList()
    )
}