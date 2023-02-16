package com.mhdsuhail.ratemyproperty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mhdsuhail.ratemyproperty.ui.propertyscreen.PropertyScreen
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
                NavHost(startDestination = Routes.PROP_VIEW_PAGE,
                navController = navController){

                    composable(Routes.PROP_VIEW_PAGE, arguments = listOf(
                        navArgument("prop_uri"){
                            type = NavType.StringType
                            defaultValue = "empty"
                        }
                    )){
                        PropertyScreen(onNavigate = {
                            navController.navigate(it.route)
                        })
                    }
                }
            }
        }
    }
}