package com.jetpack.carpartsfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import com.jetpack.carpartsfinder.view.PartsScreenView
import com.jetpack.carpartsfinder.viewmodel.PartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartFragment : Fragment() {

    private val viewModel: PartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                CarPartsFinderTheme {
                    val state = viewModel.data.observeAsState().value
                    PartsScreenView(state)
                }
//                JetsurveyTheme {
//                    WelcomeScreen(
//                        onEvent = { event ->
//                            when (event) {
//                                is WelcomeEvent.SignInSignUp -> viewModel.handleContinue(
//                                    event.email
//                                )
//                                WelcomeEvent.SignInAsGuest -> viewModel.signInAsGuest()
//                            }
//                        }
//                    )
//                }
            }
        }
    }
}
