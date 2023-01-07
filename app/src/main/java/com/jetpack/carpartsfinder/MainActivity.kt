package com.jetpack.carpartsfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import com.jetpack.carpartsfinder.view.PartsScreenView
import com.jetpack.carpartsfinder.viewmodel.PartViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CarPartsFinderTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "parts_list",
//                    modifier = modifier
                ) {
                    composable("parts_list") {
                        val viewModel: PartViewModel by viewModels()
                        PartsScreenView(parts = viewModel.state.observeAsState().value, onSearchPress = { searchString ->
                            viewModel.beginSearch(searchString)
                        }, onCardPress = {
                            navController.navigate("parts/$it")
                        })
                    }
                    composable(
                        "parts/{partId}", arguments = listOf(navArgument("partId") { type = NavType.IntType })
                    ) { backStackEntry ->
                        Text(text = "part screen" + backStackEntry.arguments?.getInt("partId"))
                    }

                }
            }
        }
    }
}

