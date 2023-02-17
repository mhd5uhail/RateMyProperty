package com.mhdsuhail.ratemyproperty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mhdsuhail.ratemyproperty.data.preview.PropertyPreviewParameterProvider
import com.mhdsuhail.ratemyproperty.ui.homescreen.HomeScreen
import com.mhdsuhail.ratemyproperty.ui.globalui.TopActionBar
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
                val items = listOf(
                    Routes.HOME_PAGE,
                    Routes.SEARCH_PAGE,
                    Routes.FAV_PAGE
                )
                val navController = rememberNavController()
                Scaffold(
                    topBar = {TopActionBar()},
                    bottomBar = {
                        BottomNavigation(backgroundColor = Color.White,
                        contentColor = Color.Gray ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    icon = {
                                        when (screen) {
                                            Routes.HOME_PAGE -> {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.home),
                                                    contentDescription = "Home Page"
                                                )
                                            }

                                            Routes.SEARCH_PAGE -> {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.search),
                                                    contentDescription = "Search Page"
                                                )
                                            }

                                            Routes.FAV_PAGE -> {
                                                Icon(
                                                    painter = painterResource(id = R.drawable.filled_favourite),
                                                    contentDescription = "Favourite Page"
                                                )
                                            }
                                        }
                                    },
                                    label = {

                                        when (screen) {
                                            Routes.HOME_PAGE -> {
                                                Text(stringResource(R.string.home_nav))
                                            }

                                            Routes.SEARCH_PAGE -> {
                                                Text(stringResource(R.string.search_nav))

                                            }

                                            Routes.FAV_PAGE -> {
                                                Text(stringResource(R.string.favourite_nav))
                                            }
                                        }

                                    },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                                    onClick = {
                                        navController.navigate(screen) {
                                            // Pop up to the start destination of the graph to
                                            // avoid building up a large stack of destinations
                                            // on the back stack as users select items
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            // Avoid multiple copies of the same destination when
                                            // reselecting the same item
                                            launchSingleTop = true
                                            // Restore state when reselecting a previously selected item
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController,
                        startDestination = Routes.SEARCH_PAGE,
                        Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.SEARCH_PAGE) {
                            SearchScreen(PropertyPreviewParameterProvider().values.toList())
                        }
                        composable(Routes.HOME_PAGE) {
                            HomeScreen()
                        }
                    }
                }

            }
        }
    }
}