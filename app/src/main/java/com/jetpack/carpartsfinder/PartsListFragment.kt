package com.jetpack.carpartsfinder

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import com.jetpack.carpartsfinder.view.PartsScreenView
import com.jetpack.carpartsfinder.viewmodel.PartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartsListFragment : Fragment() {

    private val viewModel: PartViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                CarPartsFinderTheme {
                    val state = viewModel.state.observeAsState().value
                    PartsScreenView(
                        parts = state,
                        onSearchPress = { searchString ->
                            viewModel.beginSearch(searchString)
                        },
                        onCardPress = { partId ->
                            navigate(partId)
                        }
                    )
                }
            }
        }
    }

    private fun navigate(partId: Int) {
        Log.d("1111", partId.toString())
        throw RuntimeException("Test Crash") // Force a crash

        findNavController().navigate(R.id.part_fragment)
    }
}
