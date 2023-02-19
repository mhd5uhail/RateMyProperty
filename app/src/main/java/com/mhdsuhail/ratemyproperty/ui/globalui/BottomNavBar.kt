package com.mhdsuhail.ratemyproperty.ui.globalui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.util.Routes

@Composable
fun BottomNavBar(navController: NavController, isVisible: MutableState<Boolean>) {

    val items = listOf(
        Routes.HOME_PAGE,
        Routes.SEARCH_PAGE,
        Routes.FAV_PAGE
    )
    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {

            BottomNavigation(
                backgroundColor = Color.White,
                contentColor = Color.Gray
            ) {

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
                                // re-selecting the same item
                                launchSingleTop = true
                                // Restore state when re-selecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }


            }
        })

}