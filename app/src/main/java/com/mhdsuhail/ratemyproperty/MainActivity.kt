package com.mhdsuhail.ratemyproperty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.mhdsuhail.ratemyproperty.ui.favouritescreen.FavouriteScreen
import com.mhdsuhail.ratemyproperty.ui.globalui.BottomNavBar
import com.mhdsuhail.ratemyproperty.ui.homescreen.HomeScreen
import com.mhdsuhail.ratemyproperty.ui.globalui.TopActionBar
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.PropertyScreen
import com.mhdsuhail.ratemyproperty.ui.searchscreen.SearchScreen
import com.mhdsuhail.ratemyproperty.ui.theme.RateMyPropertyTheme
import com.mhdsuhail.ratemyproperty.util.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RateMyPropertyTheme {
                val animDuration = 700
                val navController = rememberAnimatedNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val navBarState = rememberSaveable {
                    mutableStateOf(true)
                }
                val topBarTextState = rememberSaveable {
                    mutableStateOf("")
                }

                when (navBackStackEntry?.destination?.route) {
                    Routes.HOME_PAGE -> {
                        topBarTextState.value = "Welcome"
                    }
                    Routes.SEARCH_PAGE -> {
                        topBarTextState.value = "Search"
                    }
                    Routes.FAV_PAGE -> {
                        topBarTextState.value = "Favourites!"
                    }
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopActionBar(
                            onClickUserButton = {/*TODO*/ },
                            isVisible = navBarState,
                            text = topBarTextState
                        )
                    },
                    bottomBar = {
                        BottomNavBar(
                            navController = navController,
                            isVisible = navBarState
                        )
                    }
                ) { innerPadding ->
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Routes.SEARCH_PAGE,
                        modifier = Modifier.padding(innerPadding),
                    ) {
                        composable(
                            route = Routes.SEARCH_PAGE,
                            enterTransition = {fadeIn(animationSpec = tween(animDuration))} ,
                            exitTransition =  {fadeOut(animationSpec = tween(animDuration))},
                            popEnterTransition = { fadeIn(animationSpec = tween(animDuration)) },
                            popExitTransition = { fadeOut(animationSpec = tween(animDuration)) }
                        ) {
                            navBarState.value = true
                            topBarTextState.value = "Search"
                            SearchScreen(
                                onNavigate = { navController.navigate(it.route) }
                            )
                        }
                        composable(
                            route = Routes.HOME_PAGE,
                            enterTransition = {fadeIn(animationSpec = tween(animDuration))} ,
                            exitTransition =  {fadeOut(animationSpec = tween(animDuration))},
                            popEnterTransition = { fadeIn(animationSpec = tween(animDuration)) },
                            popExitTransition = { fadeOut(animationSpec = tween(animDuration)) }
                        ) {
                            navBarState.value = true
                            topBarTextState.value = "Welcome"
                            HomeScreen()
                        }

                        composable(
                            route = Routes.FAV_PAGE,
                            enterTransition = {fadeIn(animationSpec = tween(animDuration))} ,
                            exitTransition =  {fadeOut(animationSpec = tween(animDuration))},
                            popEnterTransition = { fadeIn(animationSpec = tween(animDuration)) },
                            popExitTransition = { fadeOut(animationSpec = tween(animDuration)) }
                        ) {
                            navBarState.value = true
                            topBarTextState.value = "Favourites!"
                            FavouriteScreen()
                        }
                        composable(route =
                        Routes.PROP_VIEW_PAGE + "?prop_uri={prop_uri}",
                            arguments = listOf(navArgument("prop_uri") {
                                type = NavType.StringType
                                defaultValue = ""
                            }),
                            enterTransition = {
                                scaleIn(animationSpec = tween(animDuration))
                            },
                            exitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Down,
                                    animationSpec = tween(animDuration))                            },
                            popEnterTransition = {
                                scaleIn(animationSpec = tween(animDuration))
                            },
                            popExitTransition = {
                                slideOutOfContainer(
                                    AnimatedContentScope.SlideDirection.Down,
                                    animationSpec = tween(animDuration))                                }
                        ) {
                            navBarState.value = false
                            topBarTextState.value = ""
                            PropertyScreen(
                                modifier = Modifier.animateContentSize(),
                                onNavigate = {
                                    navController.navigate(it.route)
                                }, onPopBackStack = { navController.popBackStack() })
                        }
                    }

                }

            }
        }
    }
}