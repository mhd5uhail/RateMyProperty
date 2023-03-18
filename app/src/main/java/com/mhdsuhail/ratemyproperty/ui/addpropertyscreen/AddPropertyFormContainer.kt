package com.mhdsuhail.ratemyproperty.ui.addpropertyscreen

import MileStoneProgressBar
import android.app.Application
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mhdsuhail.ratemyproperty.data.json.AssetJsonParserImpl
import com.mhdsuhail.ratemyproperty.data.preview.*
import com.mhdsuhail.ratemyproperty.ui.globalui.AnimatedFormBottomNavBar
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
                viewModel = AddPropertyScreenViewModel(
                    propertyRepository = PreviewPropertyRepository(),
                    application = Application(),
                    assetRepository = PreviewAssetRepository(),
                )
            )
        }
    }
}

fun clearNavHistory(navController: NavController) {
    navController.popBackStack(
        route = AddFormPages.AddressForm.route,
        inclusive = true
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddPropertyScreen(
    onBackToMainScreen: (UiEvent.Navigate) -> Unit,
    viewModel: AddPropertyScreenViewModel = hiltViewModel()
) {

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
    val navController = rememberAnimatedNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    LaunchedEffect(key1 = true) {

        viewModel.uiEvent.collect { event ->

            when (event) {

                is UiEvent.Navigate -> {
                    try {
                        navController.navigate(event.route)
                    } catch (e: IllegalArgumentException) {
                        clearNavHistory(navController)
                        onBackToMainScreen(event)
                    }
                }

                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.message)
                }

                is UiEvent.PopBackStack -> {
                    if (navBackStackEntry?.destination?.route == AddFormPages.AddressForm.route) {
                        clearNavHistory(navController)
                        onBackToMainScreen(UiEvent.Navigate(Routes.CONTRIBUTE_PAGE))
                    } else
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
        }, currentStep = currentStep.value)
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
                AddressForm(viewModel = viewModel)
            }

            composable(
                route = AddFormPages.AmenitiesForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = false
                currentStep.value = AddFormPages.AmenitiesForm.step
                Column(modifier = Modifier.fillMaxSize()) {
                    AmenitiesForm(viewModel = viewModel)
                }
            }

            composable(
                route = AddFormPages.PictureDescForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = false
                currentStep.value = AddFormPages.PictureDescForm.step
                PictureDescriptionForm()
            }

            composable(
                route = AddFormPages.ReviewForm.route,
                enterTransition = null,
                exitTransition = null
            ) {
                isLastPage.value = true
                currentStep.value = AddFormPages.ReviewForm.step
                ReviewForm()
            }

        }
    }
}