package com.jetpack.carpartsfinder.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jetpack.carpartsfinder.dto.PartListViewState

//@ExperimentalMaterialApi
@Composable
fun PartListScreenView(
    screenState: PartListViewState,
    onSearchPress: (String) -> Unit,
    onCardPress: (String) -> Unit,
) {
    val scaffoldState = rememberScaffoldState()

    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            scaffoldState = scaffoldState
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)
            ) {
                SearchBlockView(
//                    screenState = screenState,
                    onSearchPress = onSearchPress
                )

                if (screenState.isLoading) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                } else if (screenState.parts.isEmpty()) { //TODO when
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Can't find nothing" //TODO trans
                        )
                    }
                } else {

                    LazyColumn(
                        modifier = Modifier.fillMaxSize().padding(8.dp)
                    ) {
                        items(screenState.parts.size) { index ->
                            PartItemView(screenState.parts[index], onCardPress)
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
private fun PreviewPartListScreenView() {
    return PartListScreenView(
        screenState = PartListViewState(
            parts = listOf(),
            isLoading = false
        ),
        onCardPress = {},
        onSearchPress = {}
    )
}
