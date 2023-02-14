package com.mhdsuhail.ratemyproperty.data.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.mhdsuhail.ratemyproperty.data.Address

class AddressPreviewProvider: PreviewParameterProvider<Address> {
    override val values = sequenceOf(
        Address(null,"Canada","ON","Waterloo","350 Columbia St W","102","N2L6P1")
    )
}