package com.mhdsuhail.ratemyproperty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavArgument
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            RateMyPropertyTheme {

                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val navBarState = rememberSaveable {
                    mutableStateOf(true)
                }

                when (navBackStackEntry?.destination?.route) {
                    Routes.PROP_VIEW_PAGE -> {
                        navBarState.value = false
                    }
                    else -> {
                        navBarState.value = true
                    }
                }

                Scaffold(
                    topBar = {
                        TopActionBar(onClickUserButton = {},
                            bannerText = {
                                val currentDestination = navBackStackEntry?.destination
                                when (currentDestination?.route) {
                                    Routes.HOME_PAGE -> {
                                        "Good Morning"
                                    }
                                    Routes.SEARCH_PAGE -> {
                                        "Search"
                                    }
                                    Routes.FAV_PAGE -> {
                                        "Favourites!"
                                    }
                                    else -> ""
                                }
                            },
                            isVisible = navBarState
                        )
                    },
                    bottomBar = {
                        BottomNavBar(navController = navController,
                        isVisible = navBarState)
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Routes.SEARCH_PAGE,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.SEARCH_PAGE) {
                            navBarState.value = true
                            SearchScreen(
                                onNavigate = { navController.navigate(it.route) }
                            )
                        }
                        composable(Routes.HOME_PAGE) {
                            navBarState.value = true
                            HomeScreen()
                        }

                        composable(Routes.FAV_PAGE){
                            navBarState.value = true
                            FavouriteScreen()
                        }
                        composable(
                            Routes.PROP_VIEW_PAGE + "?prop_uri={prop_uri}",
                            arguments = listOf(navArgument("prop_uri") {
                                type = NavType.StringType
                                defaultValue = ""
                            })
                        ) {
                            navBarState.value = false
                            PropertyScreen(onNavigate = {
                                navController.navigate(it.route)
                            }, onPopBackStack = { navController.popBackStack() })
                        }
                    }
                }

            }
        }
    }
}