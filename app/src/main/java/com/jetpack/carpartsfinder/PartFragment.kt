package com.jetpack.carpartsfinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.jetpack.carpartsfinder.ui.theme.CarPartsFinderTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PartFragment : Fragment() {

//    private val viewModel: PartViewModel by viewModels()
//
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return ComposeView(requireContext()).apply {
            setContent {
                CarPartsFinderTheme {
                    Text(text = "card page view")
                }
            }
        }
    }
//
//    private fun navigate() {
//        findNavController().navigate(R.id.parts_list_fragment)
//    }
}
