package com.mhdsuhail.ratemyproperty.util

sealed class UiEvent {
    // Communication from viewModel to Ui
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackbar(
        val message: String,
        val action: String? = null,
    ): UiEvent()
}
