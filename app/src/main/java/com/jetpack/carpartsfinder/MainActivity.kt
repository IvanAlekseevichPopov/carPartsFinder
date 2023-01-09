package com.jetpack.carpartsfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import com.jetpack.carpartsfinder.view.PartListScreenView
import com.jetpack.carpartsfinder.view.SinglePartScreenView
import com.jetpack.carpartsfinder.viewmodel.PartListViewModel
import com.jetpack.carpartsfinder.viewmodel.PartViewModel
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalLifecycleComposeApi::class)
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
                        val viewModel: PartListViewModel by viewModels()
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                        PartListScreenView(
                            screenState = uiState,
                            onSearchPress = { searchString ->
                                viewModel.search(searchString)
                            },
                            onCardPress = { partId ->
                                navController.navigate("parts/$partId")
                            }
                        )
                    }

                    composable(
                        route = "parts/{id}",
                        arguments = listOf(navArgument("id") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val viewModel: PartViewModel by viewModels()
                        viewModel.searchOnePart(backStackEntry.arguments?.getString("id") ?: "")
                        val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                        SinglePartScreenView(
                            partViewState = uiState,
                        )
                    }

                }
            }
        }
    }
}

