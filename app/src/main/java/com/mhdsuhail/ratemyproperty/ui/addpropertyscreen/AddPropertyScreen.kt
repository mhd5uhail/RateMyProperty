package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import MileStoneProgressBar
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mhdsuhail.ratemyproperty.data.preview.FakePropertyRepository
import com.mhdsuhail.ratemyproperty.ui.globalui.FormBottomNavBar
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.Routes
import com.mhdsuhail.ratemyproperty.util.UiEvent


@Preview
@Composable
fun PreviewAddPropertyScreen() {
    RateMyPropertyTheme {
        Surface {
            AddPropertyScreen(
                {},
                viewModel = AddPropertyScreenViewModel(propertyRepository = FakePropertyRepository())
            )
        }
    }
}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddPropertyScreen(
    onBackPressed: () -> Unit,
    viewModel: AddPropertyScreenViewModel = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()

    val forms = remember {
        listOf(
            AddFormPages.AddressForm,
            AddFormPages.AmenitiesForm,
            AddFormPages.PictureForm,
            AddFormPages.ReviewForm
        )
    }


    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    onBackPressed()
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }

                else -> {}

            }

        }

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

    when (navBackStackEntry?.destination?.route) {
        AddFormPages.AddressForm.route -> {
            currentStep.value = AddFormPages.AddressForm.step
        }
        AddFormPages.AmenitiesForm.route -> {
            currentStep.value = AddFormPages.AmenitiesForm.step
        }
        AddFormPages.PictureForm.route -> {
            currentStep.value = AddFormPages.PictureForm.step
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
                AddressForm()
            }

            composable(
                route = AddFormPages.PictureForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                currentStep.value = AddFormPages.PictureForm.step
                AddressForm()
            }

            composable(
                route = AddFormPages.ReviewForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                currentStep.value = AddFormPages.ReviewForm.step
                AddressForm()
            }

        }
    }
}