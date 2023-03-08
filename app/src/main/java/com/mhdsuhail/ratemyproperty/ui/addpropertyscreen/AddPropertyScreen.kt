package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import MileStoneProgressBar
import android.app.Application
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.data.preview.PreviewCanadianProvinceParser
import com.mhdsuhail.ratemyproperty.ui.globalui.AnimatedFormBottomNavBar
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.UiEvent


@Preview
@Composable
fun PreviewAddPropertyScreen() {
    RateMyPropertyTheme {
        Surface {
            AddPropertyScreen(
                {},
                viewModel = AddPropertyScreenViewModel(
                    propertyRepository = FakePropertyRepository(),
                    canadianProvinceParser = PreviewCanadianProvinceParser(),
                    application = Application()
                ))
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddPropertyScreen(
    onBackToMainScreen: () -> Unit,
    viewModel: AddPropertyScreenViewModel = hiltViewModel()
) {
    val TAG = "AddPropertyScreen"

    val scaffoldState = rememberScaffoldState()
    val isLastPage = remember {
        mutableStateOf(false)
    }

    val navBarVisibility = remember {
        mutableStateOf(true)
    }
    val currentStep = remember {
        mutableStateOf(AddFormPages.AddressForm.step)
    }
    val animDuration = 700
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    if(viewModel.forms.any{ it.route == event.route })
                        navController.navigate(event.route)
                    else
                        onBackToMainScreen()
                    // If route is invalid go back to main screen
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }

                is UiEvent.PopBackStack -> {
                    if(navBackStackEntry?.destination?.route == AddFormPages.AddressForm.route)
                        onBackToMainScreen()
                    else
                        navController.popBackStack()
                }

            }

        }

    }

    when (navBackStackEntry?.destination?.route) {
        AddFormPages.AddressForm.route -> {
            currentStep.value = AddFormPages.AddressForm.step
        }
        AddFormPages.AmenitiesForm.route -> {
            currentStep.value = AddFormPages.AmenitiesForm.step
        }
        AddFormPages.PictureDescForm.route -> {
            currentStep.value = AddFormPages.PictureDescForm.step
        }
        AddFormPages.ReviewForm.route -> {
            currentStep.value = AddFormPages.ReviewForm.step
        }
    }

    Scaffold(scaffoldState = scaffoldState, topBar = {
        MileStoneProgressBar(mileStones = remember {
            val formTitleList = ArrayList<String>()
            viewModel.forms.forEach {
                formTitleList.add(it.title)
            }
            formTitleList
        }, currentStep = currentStep)
    },
        bottomBar = {
            AnimatedFormBottomNavBar(
                isVisible = navBarVisibility,
                isLastPage = isLastPage,
                onBackPressed = {
                    viewModel.onEvent(AddPropertyScreenEvents.OnBackPressed)
                },
                onNextPressed = {
                    navBackStackEntry?.destination?.route.let {
                        it?.let { viewModel.onEvent(AddPropertyScreenEvents.OnClickSubmitPage(it)) }
                    }
                }
            )
        }) { paddingValues ->

        AnimatedNavHost(
            modifier = Modifier.padding(paddingValues),
            navController = navController,
            startDestination = AddFormPages.AddressForm.route
        ) {
            composable(
                route = AddFormPages.AddressForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = false
                currentStep.value = AddFormPages.AddressForm.step
                AddressForm()
            }

            composable(
                route = AddFormPages.AmenitiesForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = false
                currentStep.value = AddFormPages.AmenitiesForm.step
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = "Amenities")
                }
            }

            composable(
                route = AddFormPages.PictureDescForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = false
                currentStep.value = AddFormPages.PictureDescForm.step
                Text(text = "Picture")
            }

            composable(
                route = AddFormPages.ReviewForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = true
                currentStep.value = AddFormPages.ReviewForm.step
                Text("Review")
            }

        }
    }
}