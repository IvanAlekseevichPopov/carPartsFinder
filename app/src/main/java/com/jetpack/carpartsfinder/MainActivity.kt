package com.jetpack.carpartsfinder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jetpack.carpartsfinder.ui.theme.AppMaterialTheme
import com.jetpack.carpartsfinder.util.RemoteConfigInterface
import com.jetpack.carpartsfinder.view.PartListScreenView
import com.jetpack.carpartsfinder.view.SinglePartScreenView
import com.jetpack.carpartsfinder.view.StartScreenView
import com.jetpack.carpartsfinder.viewmodel.PartListViewModel
import com.jetpack.carpartsfinder.viewmodel.PartViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

object Navigation {
    const val PARTS_LIST = "parts"
    const val PART = "parts/{id}"
}

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity: ComponentActivity() {

    @Inject
    lateinit var remoteConfig: RemoteConfigInterface


    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppMaterialTheme {
                if(!remoteConfig.isConfigReady.collectAsStateWithLifecycle().value) {
                    StartScreenView()
                    return@AppMaterialTheme
                }

                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = Navigation.PARTS_LIST,
                ) {
                    composable(
                        route = Navigation.PARTS_LIST,
                        content = { _ ->
                            val viewModel: PartListViewModel by viewModels()
                            PartListScreenView(
                                viewModel = viewModel,
                                onCardPress = { partId ->
                                    navController.navigate("parts/$partId")
                                }
                            )
                        }
                    )

                    composable(
                        route = Navigation.PART,
                        arguments = listOf(navArgument("id") { type = NavType.StringType }),
                        content = { backStackEntry ->
                            val viewModel: PartViewModel by viewModels()
                            viewModel.searchOnePart(backStackEntry.arguments?.getString("id") ?: "")

                            SinglePartScreenView(
                                viewModel = viewModel,
                            )
                        }
                    )
                }
            }
        }
    }

}

