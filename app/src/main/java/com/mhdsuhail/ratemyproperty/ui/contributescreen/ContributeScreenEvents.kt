package com.mhdsuhail.ratemyproperty.ui.contributescreen

sealed class ContributeScreenEvents {
    object OnClickAddProperty : ContributeScreenEvents()
    object OnClickRequestChange : ContributeScreenEvents()
}
