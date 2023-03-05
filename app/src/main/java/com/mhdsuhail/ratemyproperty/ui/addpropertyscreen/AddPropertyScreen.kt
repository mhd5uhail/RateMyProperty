package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import MileStoneProgressBar
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
import com.mhdsuhail.ratemyproperty.ui.globalui.FormBottomNavBar
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.UiEvent


@Preview
@Composable
fun PreviewAddPropertyScreen() {
    RateMyPropertyTheme {
        Surface {
            AddPropertyScreen(
                {},
                viewModel = AddPropertyScreenViewModel(propertyRepository = FakePropertyRepository())
            , onFormComplete = {})
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddPropertyScreen(
    onBackToMainScreen: () -> Unit,
    onFormComplete: (UiEvent.Navigate)-> Unit,
    viewModel: AddPropertyScreenViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    val forms = remember {
        listOf(
            AddFormPages.AddressForm,
            AddFormPages.AmenitiesForm,
            AddFormPages.PictureDescForm,
            AddFormPages.ReviewForm
        )
    }
    val navBarVisibility = remember {
        mutableStateOf(true)
    }

    val currentStep = remember {
        mutableStateOf(0)
    }
    val animDuration = 700
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()


    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    navController.navigate(event.route)
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }

                is UiEvent.PopBackStack -> {
                    if (navBackStackEntry?.destination?.route == AddFormPages.AddressForm.route) {
                        onBackToMainScreen()
                    } else {
                        navController.popBackStack()
                    }
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
            forms.map {
                formTitleList.add(it.title)
            }
            formTitleList
        }, currentStep = currentStep)
    },
        bottomBar = {
            FormBottomNavBar(
                navController = navController, isVisible = navBarVisibility, routes = remember {
                    val formRouteList = ArrayList<String>()
                    forms.map {
                        formRouteList.add(it.route)
                    }
                    formRouteList
                })
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
                currentStep.value = AddFormPages.AddressForm.step
                AddressForm()
            }

            composable(
                route = AddFormPages.AmenitiesForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                currentStep.value = AddFormPages.AmenitiesForm.step
                //AddressForm()
            }

            composable(
                route = AddFormPages.PictureDescForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                currentStep.value = AddFormPages.PictureDescForm.step
                //AddressForm()
            }

            composable(
                route = AddFormPages.ReviewForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                currentStep.value = AddFormPages.ReviewForm.step
                //AddressForm()
            }

        }
    }
}