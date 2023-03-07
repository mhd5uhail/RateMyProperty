package com.mhdsuhail.ratemyproperty.ui.globalui

import android.util.Log
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mhdsuhail.ratemyproperty.R
import com.mhdsuhail.ratemyproperty.ui.addpropertyscreen.AddFormPages
import com.mhdsuhail.ratemyproperty.ui.theme.Blue200
import java.nio.channels.NotYetConnectedException

@Composable
fun AnimatedFormBottomNavBar(
    navController: NavController,
    isVisible: MutableState<Boolean>,
    isLastPage: MutableState<Boolean>
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    AnimatedVisibility(
        visible = isVisible.value,
        enter = slideInHorizontally(initialOffsetX = { it }),
        exit = slideOutHorizontally(targetOffsetX = { it }),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .height(50.dp)
            ) {

                Box(modifier = Modifier.weight(1f)) {
                    OutlinedButton(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(5.dp)
                            .align(Alignment.Center), onClick = {
                            navController.popBackStack()
                        },
                        border = BorderStroke(width = 1.dp, color = Blue200)
                    ) {
                        Text(text = stringResource(id = R.string.back_btn))
                    }
                }
                Box(modifier = Modifier.weight(1f)) {
                    Button(modifier = Modifier
                        .fillMaxSize()
                        .padding(5.dp)
                        .align(Alignment.Center), onClick = {

                        when (navBackStackEntry?.destination?.route) {

                            AddFormPages.AddressForm.route -> {
                                navController.navigate(AddFormPages.AmenitiesForm.route)
                            }
                            AddFormPages.AmenitiesForm.route -> {
                                navController.navigate(AddFormPages.PictureDescForm.route)
                            }
                            AddFormPages.PictureDescForm.route -> {
                                navController.navigate(AddFormPages.ReviewForm.route)
                            }
                            AddFormPages.ReviewForm.route -> {
                                // On submitting the final review page form is completed
                                //TODO("Not Yet Implemented")
                            }

                        }
                    }
                    ) {
                        Text(text = let {
                            if (isLastPage.value)
                                stringResource(id = R.string.submit_btn)
                            else stringResource(id = R.string.next_btn)
                        })
                    }
                }
            }
        })

}