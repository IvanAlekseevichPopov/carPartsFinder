package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.dto.PartListViewState
import com.jetpack.carpartsfinder.dto.State
import com.jetpack.carpartsfinder.view.component.CircularProgressView
import com.jetpack.carpartsfinder.view.component.NoContentView
import com.jetpack.carpartsfinder.view.component.SearchBlockView
import com.jetpack.carpartsfinder.view.component.ServerErrorView
import com.jetpack.carpartsfinder.viewmodel.UiPartListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PartListScreenView(
    viewModel: UiPartListViewModel,
    onCardPress: (String) -> Unit,
) {
    val screenData by viewModel.uiState.collectAsState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                SearchBlockView(
                    onSearchPress = {
                        viewModel.search(it)
                    }
                )

                if (screenData.state == State.Loading) {
                    CircularProgressView()
                } else if (screenData.state == State.ServerError) {
                    ServerErrorView(onButtonClick = { viewModel.search(screenData.inputText) })
                } else if (screenData.parts.isEmpty()) {
                    NoContentView()
                } else {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    ) {
                        items(screenData.parts.size) { index ->
                            PartItemView(screenData.parts[index], onCardPress)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ContentWithProgressPreview() {
    PartListScreenView(
        viewModel = object : UiPartListViewModel {
            override val uiState: StateFlow<PartListViewState> = MutableStateFlow(PartListViewState.ServerError)
            override fun search(searchString: String?) {}
        },
        onCardPress = {}
    )
}
